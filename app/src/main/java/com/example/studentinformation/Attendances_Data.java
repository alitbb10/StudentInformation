package com.example.studentinformation;

public class Attendances_Data {
    String code,description ,date ,period ,course_name;

    public Attendances_Data(String code, String description, String date, String period, String course_name) {
        this.code = code;
        this.description = description;
        this.date = date;
        this.period = period;
        this.course_name = course_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
