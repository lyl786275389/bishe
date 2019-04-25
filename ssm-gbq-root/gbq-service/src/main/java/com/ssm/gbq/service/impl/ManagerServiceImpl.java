package com.ssm.gbq.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.ssm.gbq.dao.ManagerDao;
import com.ssm.gbq.dao.RoleDao;
import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.vo.NameDto;
import com.ssm.gbq.service.ManagerService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;
import gbq.ssm.utils.StringUtil;

/**
 * 2018年12月27日15:52:51
 * @author 阿前
 * 管理员service
 */

@Service
public class ManagerServiceImpl implements ManagerService{
    
    @Autowired
    private ManagerDao managerdao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private HttpServletRequest request;
    
    public Manager checkManagerLogin(String username, String password) throws BusinessException {
        try {
          //根据username查询
            Manager manager = managerdao.getManagerByName(username);
            //验证登录账号
            if (manager == null) {
                throw new BusinessException("用户名输入错误!");
            }
            //将登录用户的密码转化后对比
            String newPsw =StringUtil.MD5(password);
            if(!manager.getPassword().equals(newPsw)) {
                throw new BusinessException("密码不正确!");
            }
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            session.setAttribute("userInfo", manager);
//          request.getSession().setAttribute("username", manager);
            return manager;
        } catch (BusinessException e) {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Manager getCurrentManager() throws BusinessException {
        try {
            Manager manager = (Manager) request.getSession().getAttribute("userInfo");
            Manager managerById = managerdao.getManagerById(manager.getId());
            return managerById;
        } catch (Exception e) {
            throw new BusinessException("获取当前管理员失败！",e.getMessage());
        }
    }

    public PageBounds<Manager> openManagerTable(Manager manager, int currentPage, int pageSize) throws BusinessException{
        try {
            return managerdao.openManagerTable(manager,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("获取当前管理员失败！",e.getMessage());
        }
    }

    public void addManager(Manager manager,Integer roleId) {
        try {
            managerdao.addManager(manager);
            //添加权限
            roleDao.addRole(manager.getId(),roleId);
        } catch (Exception e) {
            throw new BusinessException("添加失败！",e.getMessage());
        }
    }

    public Manager getMangerByUsername(String username)throws BusinessException {
        try {
            //根据username查询
              Manager manager = managerdao.getManagerByName(username);
              return manager;
          } catch (BusinessException e) {
              
          } catch (Exception e) {
              e.printStackTrace();
          }
        return null;
    }

    public void updateManger(Manager manager) throws BusinessException{
        try {
            if (null != manager.getOldPassword()) {
                Manager exManager = managerdao.getManagerById(manager.getId());
                if (!StringUtils.equals(exManager.getPassword(),manager.getOldPassword())){
                    throw new BusinessException("旧密码输入错误");
                }else if (!StringUtils.equals(manager.getPassword(),manager.getConfirmPwd())) {
                    throw new BusinessException("新密码第一次与第二次输入的不一致");
                }
            }
            if(null != manager.getPassword()){
                manager.setPassword(StringUtil.MD5(manager.getPassword()));
            }
             managerdao.updateManger(manager);
            }catch (BusinessException e) {
                throw new BusinessException("修改失败！",e.getMessage());
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          }

    public Manager getManagerById(Integer id) throws BusinessException {
        try {
            return managerdao.getManagerById(id);
        } catch (Exception e) {
            throw new BusinessException("查询失败！",e.getMessage());
        }
    }

    public void addManagerToStudent(Integer managerId, Integer studentId) {
        try {
            managerdao.addManagerToStudent(managerId,studentId);
        } catch (Exception e) {
            throw new BusinessException("建立关联失败！",e.getMessage());
        }
    } 

    public void addManagerToTeacher(Integer managerId, Integer teacherId) {
        try {
            managerdao.addManagerToTeacher(managerId,teacherId);
        } catch (Exception e) {
            throw new BusinessException("建立关联失败！",e.getMessage());
        }
    }

    public List<Integer> searchByTeacherId(List<Integer> ids) {
        try {
            return managerdao.searchByTeacherId(ids);
        } catch (Exception e) {
            throw new BusinessException("查询关联失败！",e.getMessage());
        }
    }

    public List<Integer> searchByStudentId(List<Integer> ids) {
        try {
            return managerdao.searchByStudentId(ids);
        } catch (Exception e) {
            throw new BusinessException("查询关联失败！",e.getMessage());
        }
    }

    public void delManager(List<Integer> ids) {
        managerdao.delManager(ids);
        //删除权限
        managerdao.delRoleByManagerId(ids);
    }

    public void delManagerToStudent(List<Integer> ids) {
        managerdao.delManagerToStudent(ids);
    }

    public void delManagerToTeacher(List<Integer> ids) {
        managerdao.delManagerToTeacher(ids);
    }

    public List<NameDto> getManagerName(String name) throws BusinessException {
        try {
            return managerdao.getManagerName(name);
        } catch (Exception e) {
            throw new BusinessException("查询全部名称失败！",e.getMessage());
        }
    }

    public Manager getManagerByTeacherId(Integer id) {
        try {
            return managerdao.getManagerByTeacherId(id);
        } catch (Exception e) {
            throw new BusinessException("查询失败！",e.getMessage());
        }
    }

    public Manager getManagerByStudentId(Integer id) {
        try {
            return managerdao.getManagerByStudentId(id);
        } catch (Exception e) {
            throw new BusinessException("查询失败！",e.getMessage());
        }
    } 
}
