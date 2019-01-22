package com.example.nicolai.sensmotiongruppe5;

import android.util.Log;

import com.example.nicolai.sensmotiongruppe5.Tests.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Highscore_controller {

    DatabaseReference dbr ;



    public void createDatabaseReference(){
     dbr  = FirebaseDatabase.getInstance().getReference("scores");
     this.dbr = FirebaseDatabase.getInstance().getReference("scores");
    }




    public void addScore(Entry ent){
// id does not seem necessary, as firebase uses its own id system.
      String id =  dbr.push().getKey();

      Entry newEntry = ent;
      dbr.child(id).setValue(newEntry);
    }

    public String getKey(int id){
       String name = dbr.getKey();
if (name.isEmpty()){return "there was no entry at this id";}

        return name;
    }













}