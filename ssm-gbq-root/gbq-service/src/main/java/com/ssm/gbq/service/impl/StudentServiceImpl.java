package com.ssm.gbq.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.dao.StudentDao;
import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.News;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.vo.CustomStudentDto;
import com.ssm.gbq.model.vo.NameDto;
import com.ssm.gbq.service.StudentService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;
import gbq.ssm.utils.StringUtil;

/**
 * 学生Service
 * @author 阿前
 * 2019年1月14日10:14:28
 */
@Service
public class StudentServiceImpl implements StudentService{
    
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ManagerServiceImpl managerServiceImpl;
    @Autowired
    private NewsDao newsDao;

    public Student getStudentByPhone(String phone) throws BusinessException {
        try {
            Student student = studentDao.getStudentByPhone(phone);
            return student;
        } catch (Exception e) {
            throw new BusinessException("通过username获取当前学生信息失败！",e.getMessage());
        }
    }

    public PageBounds<Student> openStudentTable(Student student, int currentPage, int pageSize)
            throws BusinessException {
        try {
            return studentDao.openStudentTable(student,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询失败！",e.getMessage());
        }
    }

    public Student getStudentById(Integer id) throws BusinessException {
        try {
            return studentDao.getStudentById(id);
        } catch (Exception e) {
            throw new BusinessException("通过id查询失败！",e.getMessage());
        }
    }

    public void updateStudent(Student student) throws BusinessException {
        try {
            studentDao.updateStudent(student);
          //同时修改管理员的共同属性
            Manager manager = managerServiceImpl.getManagerByStudentId(student.getId());
            Manager newManager = new Manager();
            newManager.setId(manager.getId());
            newManager.setName(student.getName());
            newManager.setPhone(student.getPhone());
            newManager.setUsername(student.getPhone());
            managerServiceImpl.updateManger(newManager);
        } catch (Exception e) {
            throw new BusinessException("修改失败！",e.getMessage());
        }
    }

    public void addStudent(Student student) throws BusinessException {
        try {
            //先查询是否已经存在该学生
            Manager exmanager = managerServiceImpl.getMangerByUsername(student.getPhone());
            if (null != exmanager) {
                throw new BusinessException("此号码已经注册");
            }else if (!StringUtils.equals(student.getPassword(),student.getConfirmPwd())) {
                throw new BusinessException("俩次输入的密码不一致");
            }
            student.setCreateTime(new Date());
            studentDao.addStudent(student);
            
            //同时添加学生为三级管理员
            Manager manager = new Manager();
            manager.setName(student.getName());
            manager.setUsername(student.getPhone());
            manager.setPassword(StringUtil.MD5(student.getPassword()));
            manager.setPhone(student.getPhone());
            manager.setHeadPic("1.png");
            //添加权限
            Integer roleId = 3;
            managerServiceImpl.addManager(manager,roleId);
            
            //发送消息
            News news = new News();
            news.setCreatetime(new Date());
            news.setManagerId(manager.getId());
            news.setNews("欢迎加入");
            newsDao.addNews(news);
            
            //建立管理员与学生之间的关联
            managerServiceImpl.addManagerToStudent(manager.getId(),student.getId());
        } catch(BusinessException ex){
            throw new BusinessException(ex.getErrorMessage());
        }catch (Exception e) {
            throw new BusinessException("添加失败！",e.getMessage());
        }
    }

    public void delStudentById(List<Integer> ids) throws BusinessException {
        try {
            studentDao.delStudentById(ids);
            List<Integer>  managerIds= managerServiceImpl.searchByStudentId(ids);
            //同时删除管理员
            managerServiceImpl.delManager(managerIds);
            //删除关联
            managerServiceImpl.delManagerToStudent(ids);
        } catch (Exception e) {
            throw new BusinessException("删除失败！",e.getMessage());
        }
    }

    public void customStudent(CustomStudentDto customDto) {
        try {
            studentDao.customStudent(customDto);
            Manager managerByStudentId = managerServiceImpl.getManagerByStudentId(customDto.getStudentId());
            News news = new News();
            news.setCreatetime(new Date());
            news.setManagerId(managerByStudentId.getId());
            news.setNews("收到了老师新的评论");
            newsDao.addNews(news);
        } catch (Exception e) {
            throw new BusinessException("评论失败！",e.getMessage());
        }
    }

    public PageBounds<Student> openCustomTable(CustomStudentDto customDto, int currentPage, int pageSize)
            throws BusinessException {
        try {
            return studentDao.openCustomTable(customDto,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("分页查询失败！",e.getMessage());
        }
    }

    public PageBounds<CustomStudentDto> recordTableData(Integer studentId, int currentPage ,int pageSize) throws BusinessException {
        try {
            return studentDao.recordTableData(studentId,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("查询评分详情失败！",e.getMessage());
        }
    }

    public void customDel(List<Integer> ids) throws BusinessException {
        try {
            studentDao.customDel(ids);
        } catch (Exception e) {
            throw new BusinessException("删除失败！",e.getMessage());
        }
    }

    public List<NameDto> serchStudentByName(String studentName) throws BusinessException {
        try {
            return studentDao.serchStudentByName(studentName);
        } catch (Exception e) {
            throw new BusinessException("模糊查询名称失败！",e.getMessage());
        }
    }

    public List<Student> getStudentByCourseId(Integer courseId) throws BusinessException {
        try {
            return studentDao.getStudentByCourseId(courseId);
        } catch (Exception e) {
            throw new BusinessException("通过班级id查询失败！",e.getMessage());
        }

    }

    public List<CustomStudentDto> getCustomStudentDtos(Integer[] ids) throws BusinessException {
        try {
            return studentDao.getCustomStudentDtos(ids);
        } catch (Exception e) {
            throw new BusinessException("批量查询失败！",e.getMessage());
        }
    }

}
