package com.ssm.gbq.model.vo;

public class ResultsDto {
    
    private String name;
    private String examInput;
    /**
     * 语文成绩
     */
    private String chinese;
    /**
     * 数学成绩
     */
    private String math;
    /**
     * 英语成绩
     */
    private String english;
    /**
     * 政治成绩
     */
    private String political;
    /**
     * 历史成绩
     */
    private String history;
    /**
     * 地理成绩
     */
    private String geographic;
    
    /**
     * 考试时间
     */
    private String resultTime;
    /**
     * 
     */ 
    //private List<FuckDto> fuckDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamInput() {
        return examInput;
    }

    public void setExamInput(String examInput) {
        this.examInput = examInput;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getGeographic() {
        return geographic;
    }

    public void setGeographic(String geographic) {
        this.geographic = geographic;
    }

    public String getResultTime() {
        return resultTime;
    }

    public void setResultTime(String resultTime) {
        this.resultTime = resultTime;
    }
  
    
}
