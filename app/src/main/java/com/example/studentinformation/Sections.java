package com.example.studentinformation;

import java.util.ArrayList;
import java.util.List;

public class Sections {
    String section_name, exprsion, Start_date, End_date, Room_number, teacher_first_name,
            teacher_last_name, teacher_Email, teacher_school_phone;
    ArrayList<finalGrades> finalGrades_arr;

    public Sections(String section_name, String exprsion, String start_date, String end_date, String room_number
            , String teacher_first_name, String teacher_last_name, String teacher_Email, String teacher_school_phone
            , ArrayList<finalGrades> finalGrades_arr) {
        this.section_name = section_name;
        this.exprsion = exprsion;
        Start_date = start_date;
        End_date = end_date;
        Room_number = room_number;
        this.teacher_first_name = teacher_first_name;
        this.teacher_last_name = teacher_last_name;
        this.teacher_Email = teacher_Email;
        this.teacher_school_phone = teacher_school_phone;
        this.finalGrades_arr = finalGrades_arr;
    }

    public String getSection_name() {
        return section_name;
    }


    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getExprsion() {
        return exprsion;
    }

    public void setExprsion(String exprsion) {
        this.exprsion = exprsion;
    }

    public String getStart_date() {
        return Start_date;
    }

    public void setStart_date(String start_date) {
        Start_date = start_date;
    }

    public String getEnd_date() {
        return End_date;
    }

    public void setEnd_date(String end_date) {
        End_date = end_date;
    }

    public String getRoom_number() {
        return Room_number;
    }

    public void setRoom_number(String room_number) {
        Room_number = room_number;
    }

    public String getTeacher_first_name() {
        return teacher_first_name;
    }

    public void setTeacher_first_name(String teacher_first_name) {
        this.teacher_first_name = teacher_first_name;
    }

    public String getTeacher_last_name() {
        return teacher_last_name;
    }

    public void setTeacher_last_name(String teacher_last_name) {
        this.teacher_last_name = teacher_last_name;
    }

    public String getTeacher_Email() {
        return teacher_Email;
    }

    public void setTeacher_Email(String teacher_Email) {
        this.teacher_Email = teacher_Email;
    }

    public String getTeacher_school_phone() {
        return teacher_school_phone;
    }

    public void setTeacher_school_phone(String teacher_school_phone) {
        this.teacher_school_phone = teacher_school_phone;
    }

    public List<finalGrades> getFinalGrades_arr() {
        return finalGrades_arr;
    }

    public void setFinalGrades_arr(ArrayList<finalGrades> finalGrades_arr) {
        this.finalGrades_arr = finalGrades_arr;
    }
}
