package com.ssm.gbq.service;

import java.util.List;

import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.vo.CustomStudentDto;
import com.ssm.gbq.model.vo.NameDto;

import gbq.ssm.utils.BusinessException;
import gbq.ssm.utils.PageBounds;

public interface StudentService {
    /**
     * 分页查询
     * @param student
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Student> openStudentTable(Student student, int currentPage, int pageSize)throws BusinessException;
    /**
     * 通过id查询
     * @param tecaherId
     * @return
     * @throws BusinessException
     */
    Student getStudentById(Integer id)throws BusinessException;
    /**
     * 修改
     * @param student
     * @throws BusinessException
     */
    void updateStudent(Student student)throws BusinessException;
    /**
     * 添加
     * @param student
     */
    void addStudent(Student student)throws BusinessException;
    /**
     * 删除
     * @param ids
     * @throws BusinessException
     */
    void delStudentById(List<Integer> ids)throws BusinessException;
    /**
     * 评论
     * @param customDto
     */
    void customStudent(CustomStudentDto customDto)throws BusinessException;
    /**
     * 获取学生评价列表
     * @param customDto
     * @param currentPage
     * @param pageSize
     * @return
     * @throws BusinessException
     */
    PageBounds<Student> openCustomTable(CustomStudentDto customDto, int currentPage, int pageSize)throws BusinessException;
    /**
     * 获取评分详情
     * @param studentId
     * @param currentPage
     * @return
     * @throws BusinessException
     */
    PageBounds<CustomStudentDto> recordTableData(Integer studentId, int currentPage,int pageSize)throws BusinessException;
    /**
     * 删除不合理评价
     * @param ids
     * @throws BusinessException
     */
    void customDel(List<Integer> ids)throws BusinessException;
    /**
     * 模糊查询名称
     * @param studentName
     * @return
     * @throws BusinessException
     */
    List<NameDto> serchStudentByName(String studentName)throws BusinessException;
    /**
     * 通过手机号查询
     * @param phone
     * @return
     * @throws BusinessException
     */
    Student getStudentByPhone(String phone)throws BusinessException;
    /**
     * 通过班级id查询学生
     * @param courseId
     * @return
     * @throws BusinessException
     */
    List<Student> getStudentByCourseId(Integer courseId)throws BusinessException;
    /**
     * 通过id批量查询
     * @param ids
     * @return
     * @throws BusinessException
     */
    List<CustomStudentDto> getCustomStudentDtos(Integer[] ids)throws BusinessException;

}
