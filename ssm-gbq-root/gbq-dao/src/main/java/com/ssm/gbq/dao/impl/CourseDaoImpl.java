package com.ssm.gbq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssm.gbq.dao.CourseDao;
import com.ssm.gbq.mapper.CourseMapper;
import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.CourseExample;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;

import gbq.ssm.utils.PageBounds;

/**
 * 班级DAO
 * @author 阿前
 * 2019年1月11日10:57:27
 */

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private CourseMapper courseMapper;
    
    public List<Course> getALLCourse() throws Exception {
        CourseExample example =new CourseExample();
        return courseMapper.selectByExample(example);
    }

    public void updateCourse(Course course) throws Exception {
        courseMapper.updateByPrimaryKeySelective(course);
    }

    public Course getCousreByTeacherId(Integer teacherId) throws Exception {
        CourseExample example = new CourseExample();
        example.createCriteria().andTeacherIdEqualTo(teacherId);
        List<Course> list = courseMapper.selectByExample(example);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public PageBounds<Course> getCousreByTeacherId(Course course, int currentPage, int pageSize) throws Exception {
        final int totalSize = courseMapper.countByExample(course);
        PageBounds<Course> pageBounds = new PageBounds<Course>(currentPage, totalSize, pageSize);
        List<Course> list = courseMapper.selectByLimitPage(course, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public PageBounds<Teacher> teacherArrTableData(Integer courseId, int currentPage, int pageSize) throws Exception {
        final int totalSize = courseMapper.countToTeacher(courseId);
        PageBounds<Teacher> pageBounds = new PageBounds<Teacher>(currentPage, totalSize,pageSize);
        List<Teacher> list = courseMapper.selectToTeacherPage(courseId, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public PageBounds<Student> studenrArrTableData(Integer courseId, int currentPage, int pageSize) throws Exception {
        final int totalSize = courseMapper.countStudent(courseId);
        PageBounds<Student> pageBounds = new PageBounds<Student>(currentPage, totalSize,pageSize);
        List<Student> list = courseMapper.selectToStudentPage(courseId, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    public void courseToTeacher(Integer id, List<Integer> ids) throws Exception {
        courseMapper.courseToTeacher(id,ids);
    }

    public void addCourse(Course course) throws Exception {
        courseMapper.insertSelective(course);
    }

    public void delCourse(List<Integer> ids) throws Exception {
        courseMapper.deleteByPrimaryKey(ids);
    }

    public void delCourseToTeacher(List<Integer> ids) throws Exception {
        courseMapper.delCourseToTeacher(ids);
    }

    public Course getCousreById(Integer courseId) throws Exception {
        return courseMapper.selectByPrimaryKey(courseId);
    }


}
