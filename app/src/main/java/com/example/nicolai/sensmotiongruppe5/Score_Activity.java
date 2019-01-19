package com.example.nicolai.sensmotiongruppe5;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Score_Activity extends AppCompatActivity {


    Highscore_controller hsc;
    Button b12;
    TextView tv10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        hsc = new Highscore_controller(FirebaseDatabase.getInstance().getReference("scores"));


        // getReference("scores"));

        b12 = findViewById(R.id.button2);
        tv10 = findViewById(R.id.textView10);
        findViewById(R.id.button);
        String message = hsc.getKey(1);
        tv10.setText(message);
        Log.i("message", message);
    hsc.dbr.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String name =dataSnapshot.getValue(String.class);
            tv10.setText(name);
            Log.i("Data was changed", name);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.i("Datacancel", "onCancelled: happened in score activity");
        }
    });
    }
}
