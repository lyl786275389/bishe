package com.ssm.gbq.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.gbq.dao.NewsDao;
import com.ssm.gbq.dao.TeacherDao;
import com.ssm.gbq.model.Manager;
import com.ssm.gbq.model.News;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.service.TeacherService;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;
import gbq.ssm.utils.StringUtil;

/**
 * 2019年1月7日11:10:44
 * @author 阿前
 * 教师service
 */


@Service
public class TeacherServiceImpl implements TeacherService{
    
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ManagerServiceImpl managerServiceImpl;
    @Autowired
    private NewsDao newsDao;

    public PageBounds<Teacher> openTeacherTable(Teacher teacher, int currentPage, int pageSize)
            throws BusinessException {
        try {
            return teacherDao.openTeacherTable(teacher,currentPage,pageSize);
        } catch (Exception e) {
            throw new BusinessException("获取教师列表失败！",e.getMessage());
        }
    }

    public Teacher getTeacherById(Integer id)throws BusinessException{
        try {
            return teacherDao.getTeacherById(id);
        } catch (Exception e) {
            throw new BusinessException("通过id查询失败！",e.getMessage());
        }
    }

    public void updateTeacher(Teacher teacher) throws BusinessException {
        try {
            teacherDao.updateTeacher(teacher);
            //同时修改管理员的共同属性
            Manager manager = managerServiceImpl.getManagerByTeacherId(teacher.getId());
            Manager newManager = new Manager();
            newManager.setId(manager.getId());
            newManager.setName(teacher.getName());
            newManager.setPhone(teacher.getPhone());
            newManager.setUsername(teacher.getPhone());
            managerServiceImpl.updateManger(newManager);
            //先通过teacherId删除之前与courseId已建立的关系，即先接触老师和班级的关系
            teacherDao.delTeacherAndCourse(teacher.getId());
            //再重新建立关联
            List<Integer> coursesIds = Arrays.asList(teacher.getCoursesIds());
            teacherDao.createTeacherAndCourse(teacher.getId(),coursesIds);
        } catch (BusinessException e) {
            throw new BusinessException("修改失败！",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacher) throws BusinessException {
        try {
            Manager exmanager = managerServiceImpl.getMangerByUsername(teacher.getPhone());
            if (null != exmanager) {
                throw new BusinessException("此号码已经注册");
            }else if (!StringUtils.equals(teacher.getPassword(),teacher.getConfirmPwd())) {
                throw new BusinessException("俩次输入的密码不一致");
            }
            teacher.setCreateTime(new Date());
            teacherDao.addTeacher(teacher);
            
            //同时添加教师为二级管理员
            Manager manager = new Manager();
            manager.setName(teacher.getName());
            manager.setUsername(teacher.getPhone());
            manager.setPassword(StringUtil.MD5(teacher.getPassword()));
            manager.setPhone(teacher.getPhone());
            manager.setHeadPic("1.png");
            //添加权限
            Integer roleId = 2;
            managerServiceImpl.addManager(manager,roleId);
            
            //发送消息
            News news = new News();
            news.setCreatetime(new Date());
            news.setManagerId(manager.getId());
            news.setNews("欢迎加入");
            newsDao.addNews(news);
            
            //与管理员建立关联
            managerServiceImpl.addManagerToTeacher(manager.getId(), teacher.getId());
            
            //与班级建立关联,此时说明是管理添加老师，不是注册
            if (null != teacher.getCoursesIds()) {
                List<Integer> coursesIds = Arrays.asList(teacher.getCoursesIds());
                teacherDao.createTeacherAndCourse(teacher.getId(),coursesIds);
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Teacher getTeacherByPhone(String phone)throws BusinessException  {
        try {
            return teacherDao.getTeacherByPhone(phone);
        } catch (Exception e) {
            throw new BusinessException("通过phone查询失败！",e.getMessage());
        }
    }

    public void delTeacherById(List<Integer> ids) throws BusinessException {
        try {
            //删除老师
            teacherDao.delTeacherById(ids);
            List<Integer>  managerIds= managerServiceImpl.searchByTeacherId(ids);
            //删除老师与班级的关联
            teacherDao.delTeacherAndCourseByIds(ids);
            //删除管理员
            managerServiceImpl.delManager(managerIds);
            //删除与管理员关联
            managerServiceImpl.delManagerToTeacher(ids);
            
        } catch (Exception e) {
            throw new BusinessException("删除失败！",e.getMessage());
        }
    }

    public List<Teacher> getAllTeacher() throws BusinessException {
        try {
            return teacherDao.getAllTeacher();
        } catch (Exception e) {
            throw new BusinessException("查询全部老师失败！",e.getMessage());
        }
    }

}
