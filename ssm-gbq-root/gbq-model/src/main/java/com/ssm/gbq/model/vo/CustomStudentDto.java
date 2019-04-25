package com.ssm.gbq.model.vo;
/**
 * 评分Dto
 * @author 阿前
 * 2019年1月22日15:42:39
 */
public class CustomStudentDto {
    private Integer id;
    
    private Integer studentId;

    private Double customNum;

    private Integer teacherId;
    
    private String customText;
    
    private String teacherName;

    public Double getCustomNum() {
        return customNum;
    }

    public void setCustomNum(Double customNum) {
        this.customNum = customNum;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCustomText() {
        return customText;
    }

    public void setCustomText(String customText) {
        this.customText = customText;
    }
    
    private String name;
    
    private String orderByClause;

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
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
    
    
}
