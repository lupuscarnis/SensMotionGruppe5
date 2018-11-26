package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Launch Login Activity.
        startActivity(new Intent(MainActivity.this, Login_Activity.class));

        //Alan

    }
}
