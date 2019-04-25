package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;

import gbq.ssm.utils.PageBounds;

public interface CourseDao {
    /**
     * 获取全部班级信息
     * @return
     * @throws Exception
     */
    List<Course> getALLCourse()throws Exception;
    /**
     * 修改
     * @param course
     * @throws Exception
     */
    void updateCourse(Course course)throws Exception;
    /**
     * 通过班主任id查询
     * @param teacherId
     * @return
     * @throws Exception
     */
    Course getCousreByTeacherId(Integer teacherId)throws Exception;
    /**
     * 分页查询
     * @param course
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Course> getCousreByTeacherId(Course course, int currentPage, int pageSize)throws Exception;
    /**
     * 获取教师详情
     * @param teacherId
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Teacher> teacherArrTableData(Integer courseId, int currentPage, int pageSize)throws Exception;
    /**
     * 获取班内学生信息
     * @param courseId
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Student> studenrArrTableData(Integer courseId, int currentPage, int pageSize)throws Exception;
    /**
     * 班级与任教老师之间建立关联
     * @param id
     * @param list
     * @throws Exception
     */
    void courseToTeacher(Integer id, List<Integer> ids)throws Exception;
    /**
     * 添加
     * @param course
     * @throws Exception
     */
    void addCourse(Course course)throws Exception;
    /**
     * 删除
     * @param ids
     * @throws Exception
     */
    void delCourse(List<Integer> ids)throws Exception;
    /**
     * 删除班级与老师之间的关联
     * @param ids
     * @throws Exception
     */
    void delCourseToTeacher(List<Integer> ids)throws Exception;
    /**
     * 通过id查询
     * @param courseId
     * @return
     * @throws Exception
     */
    Course getCousreById(Integer courseId)throws Exception;

}
