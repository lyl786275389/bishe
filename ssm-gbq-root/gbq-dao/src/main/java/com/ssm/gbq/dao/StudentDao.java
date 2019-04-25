package com.ssm.gbq.dao;

import java.util.List;

import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.vo.CustomStudentDto;
import com.ssm.gbq.model.vo.NameDto;

import gbq.ssm.utils.PageBounds;

public interface StudentDao {
    /**
     * 分页查询
     * @param student
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Student> openStudentTable(Student student, int currentPage, int pageSize)throws Exception;
    /**
     * 通过id查询
     * @param id
     * @return
     * @throws Exception
     */
    Student getStudentById(Integer id)throws Exception;
    /**
     * 修改
     * @param student
     */
    void updateStudent(Student student)throws Exception;
    /**
     * 添加
     * @param student
     * @throws Exception
     */
    void addStudent(Student student)throws Exception;
    /**
     * 通过手机查询
     * @param phone
     * @return
     * @throws Exception
     */
    Student getStudentByPhone(String phone)throws Exception;
    /**
     * 删除
     * @param ids
     * @throws Exception
     */
    void delStudentById(List<Integer> ids)throws Exception;
    /**
     * 评价
     * @param customDto
     * @throws Exception
     */
    void customStudent(CustomStudentDto customDto)throws Exception;
    /**
     * 获取学生评价列表
     * @param customDto
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageBounds<Student> openCustomTable(CustomStudentDto customDto, int currentPage, int pageSize)throws Exception;
    /**
     * 获取学生评价详情
     * @param studentId
     * @param currentPage
     * @return
     * @throws Exception
     */
    PageBounds<CustomStudentDto> recordTableData(Integer studentId, int currentPage,int pageSize)throws Exception;
    /**
     * 删除不合理评价
     * @param ids
     * @throws Exception
     */
    void customDel(List<Integer> ids)throws Exception;
    /**
     * 模糊查询名称
     * @param studentName
     * @return
     * @throws Exception
     */
    List<NameDto> serchStudentByName(String studentName)throws Exception;
    /**
     * 查询全部学生
     * @return
     * @throws Exception
     */
    List<Student> getAllStudent();
    /**
     * 通过班级id查询
     * @param courseId
     * @return
     * @throws Exception
     */
    List<Student> getStudentByCourseId(Integer courseId)throws Exception;
    /**
     * 通过id批量查询
     * @param ids
     * @return
     * @throws Exception
     */
    List<CustomStudentDto> getCustomStudentDtos(Integer[] ids)throws Exception;

}
