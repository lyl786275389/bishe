package com.ssm.gbq.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum ExamStatusEnum {
    CenterExam(1,"期中考试"),EndExam(2,"期末考试");
    
    private ExamStatusEnum(int id,String name){
        this.id = id;
        this.name = name;
    }
private static final List<ExamStatusEnum> Examies = new ArrayList<ExamStatusEnum>();
    
    static {
        Examies.add(CenterExam);
        Examies.add(EndExam);
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
    public static List<ExamStatusEnum> getexamiesEnum() {
        return Examies;
    }
    
    public static String getNameById(int id){
        for (ExamStatusEnum examiesEnum : ExamStatusEnum.values()) {
            if (examiesEnum.getId() == id) {
                return examiesEnum.getName();
            }
        }
        return "";
    }
    
    public static Integer getIdByName(String name){
        for (ExamStatusEnum examiesEnum : ExamStatusEnum.values()) {
            if (examiesEnum.getName().equals(name)) {
                return examiesEnum.getId();
            }
        }
        return null;
    }
}
