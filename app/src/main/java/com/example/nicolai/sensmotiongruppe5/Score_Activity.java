package com.example.nicolai.sensmotiongruppe5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

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
        String message = hsc.getName(1);
        tv10.setText(message);
        Log.i("message", message);

    }
}
