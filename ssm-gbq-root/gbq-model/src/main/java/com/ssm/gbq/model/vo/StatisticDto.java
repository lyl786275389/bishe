package com.ssm.gbq.model.vo;
/**
 * 统计首页数量Dto
 * @author 阿前
 *
 */
public class StatisticDto {
    /**
     * 超级管理员数量
     */
    private Integer superAdminCount;
    /**
     * 老师数量
     */
    private Integer teacherCount;
    
    /**
     * 学生数量
     */
    private Integer studentCount;
    /**
     * 班级数量
     */
    private Integer courseCount;

    public Integer getSuperAdminCount() {
        return superAdminCount;
    }

    public void setSuperAdminCount(Integer superAdminCount) {
        this.superAdminCount = superAdminCount;
    }

    public Integer getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(Integer teacherCount) {
        this.teacherCount = teacherCount;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    
}
