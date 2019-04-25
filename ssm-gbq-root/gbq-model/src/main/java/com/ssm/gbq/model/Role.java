package com.ssm.gbq.model;
/**
 * 权限Model
 * @author 阿前
 *
 */
public class Role {
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限识别名
     */
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}