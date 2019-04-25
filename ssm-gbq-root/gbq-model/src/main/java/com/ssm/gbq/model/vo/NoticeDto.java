package com.ssm.gbq.model.vo;

public class NoticeDto {
    /**
     * 公告内容
     */
    private String notice;
    /**
     * 创建时间
     */
    private String createTime;
    public String getNotice() {
        return notice;
    }
    public void setNotice(String notice) {
        this.notice = notice;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    
}
