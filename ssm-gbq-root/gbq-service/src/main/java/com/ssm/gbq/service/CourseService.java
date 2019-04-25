package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface CourseService {
    /**
     * 获取全班级信息
     * @return
     * @throws BusinessException
     */
    List<Course> getALLCourse()throws BusinessException;
    /**
     * 修改
     * @param courseId
     * @param id
     * @throws BusinessException
     */
    void updateCourse(Course course)throws BusinessException;
    /**
     * 通过班主任id查询
     * @param teacherId
     * @return
     * @throws BusinessException
     */
    Course getCousreByTeacherId(Integer teacherId)throws BusinessException;
    /**
     * 分页查询
     * @param course
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Course> openCourseTable(Course course, int currentPage, int pageSize)throws BusinessException;
    /**
     * 获取教师详情
     * @param teacherId
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Teacher> teacherArrTableData(Integer courseId, int currentPage, int pageSize)throws BusinessException;
    /**
     * 获取班级内学生信息
     * @param courseId
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Student> studenrArrTableData(Integer courseId, int currentPage, int pageSize)throws BusinessException;
    /**
     * 添加
     * @param course
     * @throws BusinessException
     */
    void addCourse(Course course)throws BusinessException;
    /**
     * 删除 
     * @param ids
     * @throws BusinessException
     */
    void delCourse(List<Integer> ids)throws BusinessException;
    /**
     * 通过id查
     * @param courseId
     * @return
     */
    Course getCousreById(Integer courseId)throws BusinessException;

}
