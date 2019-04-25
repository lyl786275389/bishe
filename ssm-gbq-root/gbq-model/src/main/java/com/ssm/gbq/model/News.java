package com.ssm.gbq.model;

import java.util.Date;
import java.util.List;

import gbq.ssm.utils.StringUtil;

public class News {
    private Integer id;

    private String news;

    private Date createtime;

    private Integer managerId;

    private Integer state;

    private String remark;
    
    private List<Integer> managerIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news == null ? null : news.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public List<Integer> getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(List<Integer> managerIds) {
        this.managerIds = managerIds;
    }
    
    private String orderByClause;
    
    private String startTime;
    
    private String endTime;

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
        return StringUtil.dateToStringToS(createtime);
    }
    
    
}