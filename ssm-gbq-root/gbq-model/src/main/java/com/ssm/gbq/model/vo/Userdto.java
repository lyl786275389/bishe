package com.ssm.gbq.model.vo;

/**
 *  注册用户dto
 * @author 阿前
 * 2019年1月14日15:15:43
 */
public class Userdto {
    private String name;
    private String phone;
    private String username;
    private String password;
    private String confirmPwd;
    private Integer identityId;
    private Integer subjectId;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPwd() {
        return confirmPwd;
    }
    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
    public Integer getIdentityId() {
        return identityId;
    }
    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    
}
