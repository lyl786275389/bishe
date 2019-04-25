package com.ssm.gbq.model;

import java.util.Date;

import gbq.ssm.utils.StringUtil;
/**
 * 公告/通知
 * @author 阿前
 * 2019年1月30日15:48:19
 */
public class Notice {
    private Integer id;
    /**
     * 公告内容
     */
    private String notice;
    
    private Integer managerId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否在首页公告栏中显示0：不显示 1显示 2申请
     */
    private Short state;
    /**
     * 0:公告 1表演 2批评    
     */
    private Short rose;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
    
    public Short getRose() {
        return rose;
    }

    public void setRose(Short rose) {
        this.rose = rose;
    }
    
    public String getRoseStr(){
        if(this.rose == 0){
            return "公告";
        }else if (this.rose == 1) {
            return "表扬";
        }else if(this.rose == 2) {
            return "批评";
        }
        return null;
    }


    /**
     *前台传回的参数
     */
    private String name;
    
    private String orderByClause;
    
    private Integer[] managerIds;
    
    private String startTime;
    
    private String endTime;

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
    public String getCreateTimeStr(){
        return StringUtil.dateToStringToS(createTime);
    }

    public Integer[] getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(Integer[] managerIds) {
        this.managerIds = managerIds;
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
    
}