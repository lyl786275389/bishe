package com.ssm.gbq.model.enums;

import java.util.ArrayList;
import java.util.List;


/**
 * 课程枚举
 * @author 阿前
 * 2019年1月9日17:41:06
 */
public enum SubjectEnum {
    Chinese(1,"语文"),Math(2,"数学"),English(3,"英语"),History(4,"历史"),Politics(5,"政治"),Geography(6,"地理");
    
    private SubjectEnum(int id,String name){
        this.id = id;
        this.name = name;
    }
    
    private static final List<SubjectEnum> Clasies = new ArrayList<SubjectEnum>();
    
    static {
        Clasies.add(Chinese);
        Clasies.add(Math);
        Clasies.add(English);
        Clasies.add(History);
        Clasies.add(Politics);
        Clasies.add(Geography);
    }
    
    private int id;
    
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static List<SubjectEnum> getClassEnum() {
        return Clasies;
    }
    
    public static String getNameById(int id){
        for (SubjectEnum classEnum : SubjectEnum.values()) {
            if (classEnum.getId() == id) {
                return classEnum.getName();
            }
        }
        return "";
    }
    
    public static Integer getIdByName(String name){
        for (SubjectEnum classEnum : SubjectEnum.values()) {
            if (classEnum.getName().equals(name)) {
                return classEnum.getId();
            }
        }
        return null;
    }
    
   
    
}
