package com.ssm.gbq.model.vo;

public class FuckDto {
    private String name;
    private String examInput;
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
    private String resultTime;
    /**
     * 
     */
    
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
    public String getResultTime() {
        return resultTime;
    }
    public void setResultTime(String resultTime) {
        this.resultTime = resultTime;
    }
    public String getExamInput() {
        return examInput;
    }
    public void setExamInput(String examInput) {
        this.examInput = examInput;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
