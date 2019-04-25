package com.ssm.gbq.model;

import java.util.List;

/**
 * 班级Model
 * @author 阿前
 *
 */
public class Course {
    private Integer id;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 别名
     */
    private String cname;
    
    /**
     * 备注/标签
     */
    private String remark;
    /**
     * 学生id  一对一
     */
    private Integer studentId;
    /**
     * 教师id 一对一
     */
    private Integer teacherId;
    
    private String teacherName;
    
    private String studentName;
    
    private List<Teacher> teachers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    
    /**
     *前台传回的参数
     */
    private String name;
    
    private String orderByClause;
    
    private Integer[] teacherIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Integer[] getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(Integer[] teacherIds) {
        this.teacherIds = teacherIds;
    }

}