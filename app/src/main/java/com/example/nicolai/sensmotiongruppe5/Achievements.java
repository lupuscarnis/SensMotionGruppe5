package com.example.nicolai.sensmotiongruppe5;

public class Achievements {

private String achiName;
private boolean completed;
private String description;

    public Achievements(String achiName, boolean completed, String description){

        this.achiName = achiName;
        this.completed = completed;
        this.description = description;
    }
    public boolean getCompleted() {

        return completed;
    }
    public String getName() {

        return achiName;
    }

















}
