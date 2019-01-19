package com.example.nicolai.sensmotiongruppe5;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Highscore_controller {

    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("scores");

    public Highscore_controller(DatabaseReference dbr) {
        this.dbr = dbr;
    }


    private class entry{
     String name, id;
     int score;


        public entry(String name, int score, String id) {
            this.name = name;
            this.score = score;
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


    public void addScore(entry ent){

      String id =  dbr.push().getKey();

      entry newEntry = ent;
      dbr.child(id).setValue(newEntry);
    }

    public String getKey(int id){
       String name = dbr.getKey();
if (name.isEmpty()){return "there was no entry at this id";}

        return name;
    }













}