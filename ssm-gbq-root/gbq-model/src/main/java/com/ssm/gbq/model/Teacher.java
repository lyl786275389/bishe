package com.ssm.gbq.model;

import java.util.Date;
import java.util.List;

import com.ssm.gbq.model.enums.SubjectEnum;
import gbq.ssm.utils.StringUtil;
/**
 * 教师model
 * @author 28937
 *
 */
public class Teacher {
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 一对一  课程
     */
    private Integer subjectId;
    
    
    private Integer courseId;
    
    /**
     * 任教班级 多对多
     */
    private List<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }


    /**
     * 前台传回的参数
     */
    private String startTime;
    
    private String endTime;
    
    private String confirmPwd;
    
    private String orderByClause;
    
    private Integer[] coursesIds;
    
    /**
     * 教师登录密码
     */
    private String password;

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getCreateTimeStr(){
        return StringUtil.dateToStringToS(createTime);
    }
    
    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
    

    public Integer[] getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(Integer[] coursesIds) {
        this.coursesIds = coursesIds;
    }

    /**
     * 任教课程
     * @return
     */
    public String getClassEnumString(){
        if (null != subjectId) {
            return SubjectEnum.getNameById(this.subjectId);
        }
        return "";
    }
    
}