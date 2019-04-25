package com.ssm.gbq.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ssm.gbq.model.enums.ExamStatusEnum;

import gbq.ssm.utils.StringUtil;

public class Results {
    private Integer id;
    /**
     * 语文成绩
     */
    private Double chinese;
    /**
     * 数学成绩
     */
    private Double math;
    /**
     * 英语成绩
     */
    private Double english;
    /**
     * 政治成绩
     */
    private Double political;
    /**
     * 历史成绩
     */
    private Double history;
    /**
     * 地理成绩
     */
    private Double geographic;
    /**
     * 考试时间
     */
    private Date examTime;
    /**
     * 1：期中考试；2：期末考试
     */
    private Integer examStatus;
    
    private Integer studentId;
    /**
     * 是否移除到档案
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getChinese() {
        return chinese;
    }

    public void setChinese(Double chinese) {
        this.chinese = chinese;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getEnglish() {
        return english;
    }

    public void setEnglish(Double english) {
        this.english = english;
    }

    public Double getPolitical() {
        return political;
    }

    public void setPolitical(Double political) {
        this.political = political;
    }

    public Double getHistory() {
        return history;
    }

    public void setHistory(Double history) {
        this.history = history;
    }

    public Double getGeographic() {
        return geographic;
    }

    public void setGeographic(Double geographic) {
        this.geographic = geographic;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Integer getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(Integer examStatus) {
        this.examStatus = examStatus;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    /**
     * 前台传回的参数
     */
    private String startTime;
    
    private String endTime;
    
    private String name;
    
    private String examTimes;
    
    
    public String getExamTimes() {
        return examTimes;
    }

    public void setExamTimes(String examTimes) {
        this.examTimes = examTimes;
    }

    private String orderByClause;

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

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCreateTimeStr(){
        return StringUtil.dateToStringToSs(examTime);
    }
    /**
     * 考试时间
     * @return
     */
    public String getClassEnumString(){
        if (null != examStatus) {
            return this.getCreateTimeStr()+"("+ExamStatusEnum.getNameById(this.examStatus)+")";
        }
        return "";
    }
    
    private Double sumResult;
    /**
     * 总成绩
     */
    public Double getSumResult() {
        this.sumResult = this.chinese + this.math + this.english + this.political + this.history +this.geographic;
        return this.sumResult;
    }
    
    public void setSumResult(Double sumResult) {
        this.sumResult = sumResult;
    }

    /**
     * 平均成绩
     */
    public Double getPjResult() {
        BigDecimal b1 = new BigDecimal(Double.toString(sumResult));
        BigDecimal b2 = new BigDecimal(6);
        double doubleValue = b1.divide(b2,1,BigDecimal.ROUND_HALF_UP).doubleValue();
        return doubleValue;
    }
}