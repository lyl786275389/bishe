package com.ssm.gbq.mapper;

import com.ssm.gbq.model.Teacher;
import com.ssm.gbq.model.TeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int countByExample(@Param("example")Teacher teacher);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectByLimitPage(@Param("example")Teacher teacher,@Param("offset") int offset, @Param("limit")int pageSize);

    void delTeacherAndCourse(Integer teacherId);

    void createTeacherAndCourse(@Param("teacherId")Integer teacherId, @Param("coursesIds")List<Integer> coursesIds);

    void delTeacherAndCourseByIds(List<Integer> ids);
}