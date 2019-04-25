package com.ssm.gbq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.StudentExample;
import com.ssm.gbq.model.vo.CustomStudentDto;
import com.ssm.gbq.model.vo.NameDto;

public interface StudentMapper {
    int countByExample(@Param("example")Student student);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectByLimitPage(@Param("example")Student student, @Param("offset")int offset, @Param("limit")int pageSize);

    void customStudent(CustomStudentDto customDto);

    int countByCustom(@Param("example")CustomStudentDto customDto);

    List<Student> selectByCustomPage(@Param("example")CustomStudentDto customDto, @Param("offset")int offset,@Param("limit") int pageSize);

    int countToCustom(@Param("studentId")Integer studentId);

    List<CustomStudentDto> selectToCustomPage(@Param("studentId")Integer studentId, @Param("offset")int offset,@Param("limit") int pageSize);

    void customDel(List<Integer> ids);

    List<NameDto> serchStudentByName(@Param("studentName")String studentName);

    List<CustomStudentDto> getCustomStudentDtos(List<Integer> list);

}