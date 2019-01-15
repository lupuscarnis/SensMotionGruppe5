package com.example.nicolai.sensmotiongruppe5;

public class Achievements {

private String achiName;
private boolean completed;
private String doneDescription;
private String notDescription;

    public Achievements(String achiName, boolean completed, String doneDescription, String notDectription){

        this.achiName = achiName;
        this.completed = completed;
        this.doneDescription = doneDescription;
        this.notDescription = notDectription;
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










    


}
