package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.Teacher;

import gbq.ssm.utils.PageBounds;

public interface TeacherDao {
    /**
     * 获取教师列表
     * @param teacher
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Teacher> openTeacherTable(Teacher teacher, int currentPage, int pageSize)throws Exception;
    /**
     * 通过id查询教师信息
     * @param id
     * @return
     * @throws Exception
     */
    Teacher getTeacherById(Integer id)throws Exception;
    /**
     * 修改
     * @param teacher
     */
    void updateTeacher(Teacher teacher)throws Exception;
    /**
     * 添加
     * @param teacher
     * @throws Exception
     */
    void addTeacher(Teacher teacher)throws Exception;
    /**
     * 通过手机号查询
     * @param phone
     * @return
     * @throws Exception
     */
    Teacher getTeacherByPhone(String phone)throws Exception;
    /**
     * 通过teacherId解除老师与所在班级的联系
     * @param id
     */
    void delTeacherAndCourse(Integer teacherId)throws Exception;
    /**
     * 通过teacherId和coursesId建立联系
     * @param id
     * @param coursesIds
     */
    void createTeacherAndCourse(Integer teacherId, List<Integer> coursesIds)throws Exception;
    /**
     * 通过id删除老师
     * @param ids
     */
    void delTeacherById(List<Integer> ids)throws Exception;
    /**
     * 通过id删除老师与其任教班级取消联系
     * @param ids
     * @throws Exception
     */
    void delTeacherAndCourseByIds(List<Integer> ids)throws Exception;
    /**
     * 查询全部老师
     * @return
     * @throws Exception
     */
    List<Teacher> getAllTeacher()throws Exception;

}
