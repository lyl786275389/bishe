package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.Teacher;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface TeacherService {
    /**
     * 获取教师列表
     * @param teacher
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Teacher> openTeacherTable(Teacher teacher, int currentPage, int pageSize)throws BusinessException;
    
    /**
     * 通过id查询
     * @param id
     * @return
     */
    Teacher getTeacherById(Integer id)throws BusinessException;
    
    /**
     * 修改
     * @param teacher
     * @throws BusinessException
     */
    void updateTeacher(Teacher teacher)throws BusinessException;
    
    /**
     * 添加
     * @param teacher
     * @throws BusinessException
     */
    void addTeacher(Teacher teacher)throws BusinessException;
    
    /**
     * 通过phone查询
     * @param phone
     * @return
     */
    Teacher getTeacherByPhone(String phone)throws BusinessException;
    
    /**
     * 通过id删除老师
     * @param ids
     */
    void delTeacherById(List<Integer> ids)throws BusinessException;
    /**
     * 获取全部老师
     * @return
     * @throws BusinessException
     */
    List<Teacher> getAllTeacher()throws BusinessException;

}
