package com.ssm.gbq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.TeacherDao;
import com.ssm.gbq.mapper.TeacherMapper;
import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.model.TeacherExample;

import gbq.ssm.utils.PageBounds;

/**
 * 教师Dao
 * @author 阿前
 * 2019年1月7日11:14:30
 */

@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Autowired 
    private TeacherMapper teacherMapper;

    public PageBounds<Teacher> openTeacherTable(Teacher teacher, int currentPage, int pageSize) throws Exception {
        final int totalSize = teacherMapper.countByExample(teacher);
        PageBounds<Teacher> pageBounds = new PageBounds<Teacher>(currentPage, totalSize, pageSize);
        List<Teacher> list = teacherMapper.selectByLimitPage(teacher, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public Teacher getTeacherById(Integer id) throws Exception {
        return teacherMapper.selectByPrimaryKey(id);
    }

    public void updateTeacher(Teacher teacher) throws Exception {
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    public void addTeacher(Teacher teacher) throws Exception {
        teacherMapper.insertSelective(teacher);
    }

    public Teacher getTeacherByPhone(String phone) throws Exception {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andPhoneEqualTo(phone);
        List<Teacher> list = teacherMapper.selectByExample(example);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public void delTeacherAndCourse(Integer teacherId)throws Exception  {
        teacherMapper.delTeacherAndCourse(teacherId);
    }

    public void createTeacherAndCourse(Integer teacherId, List<Integer> coursesIds)throws Exception  {
        teacherMapper.createTeacherAndCourse(teacherId,coursesIds);
    }

    public void delTeacherById(List<Integer> ids) throws Exception {
        teacherMapper.deleteByPrimaryKey(ids);
    }

    public void delTeacherAndCourseByIds(List<Integer> ids) throws Exception {
        teacherMapper.delTeacherAndCourseByIds(ids);
    }

    public List<Teacher> getAllTeacher() throws Exception {
        TeacherExample example = new TeacherExample();
        return teacherMapper.selectByExample(example);
    }

}
