package com.ssm.gbq.mapper;

import com.ssm.gbq.model.Course;
import com.ssm.gbq.model.CourseExample;
import com.ssm.gbq.model.Student;
import com.ssm.gbq.model.Teacher;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {
    int countByExample(@Param("example")Course course);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(List<Integer> ids);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    //查询任课班级
    List<Course> selectCourseByTeacherId(Integer teacherId);

    List<Course> selectByLimitPage(@Param("example")Course course, @Param("offset")int offset, @Param("limit")int pageSize);

    int countToTeacher(@Param("courseId")Integer courseId);

    List<Teacher> selectToTeacherPage(@Param("courseId")Integer courseId, @Param("offset")int offset, @Param("limit")int pageSize);

    int countStudent(@Param("courseId")Integer courseId);

    List<Student> selectToStudentPage(@Param("courseId")Integer courseId, @Param("offset")int offset,  @Param("limit")int pageSize);

    void courseToTeacher(@Param("courseId")Integer id, @Param("teacherIds")List<Integer> ids);

    void delCourseToTeacher(List<Integer> ids);
}