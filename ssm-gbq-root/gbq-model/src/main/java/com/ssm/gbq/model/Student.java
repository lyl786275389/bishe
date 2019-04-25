package com.ssm.gbq.model;

import java.util.Date;
import java.util.List;

import com.ssm.gbq.model.vo.CustomStudentDto;

import gbq.ssm.utils.StringUtil;

public class Student {
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    private Integer courseId;
    
    private Course course;
    
    private Double customNumPj;
    
    private List<CustomStudentDto> customDto;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    /**
     * 前台传回的参数
     */
    private String startTime;
    
    private String endTime;
    
    private String confirmPwd;
    
    private String orderByClause;
    
    /**
     * 密码
     */
    private String password;

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

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    
    public String getCreateTimeStr(){
        return StringUtil.dateToStringToS(createTime);
    }

    public List<CustomStudentDto> getCustomDto() {
        return customDto;
    }

    public void setCustomDto(List<CustomStudentDto> customDto) {
        this.customDto = customDto;
    }

    public Double getCustomNumPj() {
        return customNumPj;
    }

    public void setCustomNumPj(Double customNumPj) {
        this.customNumPj = customNumPj;
    }

    
}