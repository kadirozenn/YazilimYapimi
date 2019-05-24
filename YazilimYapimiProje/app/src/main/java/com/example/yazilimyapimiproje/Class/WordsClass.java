package com.example.yazilimyapimiproje.Class;

public class WordsClass {

    private String word;
    private String remainingDay;
    private String ID;
    private String status;



    public WordsClass(String ID, String word, String remainingDays, String status) {
        this.word = word;
        this.remainingDay = remainingDays;
        this.ID = ID;
        this.status=status;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getRemainingDay() {
        return remainingDay;
    }

    public void setRemainingDay(String remainingDay) {
        this.remainingDay = remainingDay;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
