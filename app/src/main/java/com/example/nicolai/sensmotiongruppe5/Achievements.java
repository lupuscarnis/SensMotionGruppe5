package com.example.nicolai.sensmotiongruppe5;

public class Achievements {

    private String achiName;
    private boolean completed;
    private String doneDescription;
    private String notDescription;
    private String date;

    public Achievements(String achiName, boolean completed, String doneDescription, String notDectription, String date) {

        this.achiName = achiName;
        this.completed = completed;
        this.doneDescription = doneDescription;
        this.notDescription = notDectription;
        this.date = date;
    }

    public boolean getCompleted() {

        return completed;
    }

    public boolean setCompleted(boolean completed) {

        this.completed = completed;

        return true;
    }


    public String getName() {

        return achiName;
    }

    public String getDoneDes() {

        return doneDescription;
    }

    public String getNotDes() {

        return notDescription;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;

    }


}
