package com.example.studentinformation;

import java.util.ArrayList;

public class Student {
    private String image, first_name, middle_name, last_name, Graudte, grade_level, student_id, gpa, Curr_term,
            Start_Meal_balance, Curr_Meal_balance, gender, Ethnicity;
    ArrayList<Sections> sections_arr;
    ArrayList<Attendances_Data> attendances_data;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGraudte() {
        return Graudte;
    }

    public void setGraudte(String graudte) {
        Graudte = graudte;
    }

    public String getGrade_level() {
        return grade_level;
    }

    public void setGrade_level(String grade_level) {
        this.grade_level = grade_level;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getCurr_term() {
        return Curr_term;
    }

    public void setCurr_term(String curr_term) {
        Curr_term = curr_term;
    }

    public String getStart_Meal_balance() {
        return Start_Meal_balance;
    }

    public void setStart_Meal_balance(String start_Meal_balance) {
        Start_Meal_balance = start_Meal_balance;
    }

    public String getCurr_Meal_balance() {
        return Curr_Meal_balance;
    }

    public void setCurr_Meal_balance(String curr_Meal_balance) {
        Curr_Meal_balance = curr_Meal_balance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return Ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        Ethnicity = ethnicity;
    }

    public ArrayList<Sections> getSections_arr() {
        return sections_arr;
    }

    public void setSections_arr(ArrayList<Sections> sections_arr) {
        this.sections_arr = sections_arr;
    }

    public ArrayList<Attendances_Data> getAttendances_data() {
        return attendances_data;
    }

    public void setAttendances_data(ArrayList<Attendances_Data> attendances_data) {
        this.attendances_data = attendances_data;
    }

    public Student(String image, String first_name, String middle_name, String last_name, String graudte, String grade_level, String student_id, String gpa, String curr_term, String start_Meal_balance, String curr_Meal_balance, String gender, String ethnicity, ArrayList<Sections> sections_arr, ArrayList<Attendances_Data> attendances_data) {
        this.image = image;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        Graudte = graudte;
        this.grade_level = grade_level;
        this.student_id = student_id;
        this.gpa = gpa;
        Curr_term = curr_term;
        Start_Meal_balance = start_Meal_balance;
        Curr_Meal_balance = curr_Meal_balance;
        this.gender = gender;
        Ethnicity = ethnicity;
        this.sections_arr = sections_arr;
        this.attendances_data = attendances_data;
    }
}