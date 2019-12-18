package com.example.studentinformation;

public class finalGrades {
    String test_id, percent, letter, eval, comment, start_date, end_date;

    public finalGrades(String test_id, String percent, String letter, String eval, String comment, String start_date, String end_date) {
        this.test_id = test_id;
        this.percent = percent;
        this.letter = letter;
        this.eval = eval;
        this.comment = comment;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getEval() {
        return eval;
    }

    public void setEval(String eval) {
        this.eval = eval;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
