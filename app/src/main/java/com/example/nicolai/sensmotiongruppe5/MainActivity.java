package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Launch Login Activity. Use "@" in email to get past it.
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

        //Alan

    }
}
