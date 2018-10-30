package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.support.v4.content.ContextCompat.startActivity;

public class Forside extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        // Launch Login Activity. Use "@" in email to get past it.
        startActivity(new Intent(Forside.this, LoginActivity.class));
        //Alan

    }



public void helped(View v){
     Intent   i = new Intent(this, Help.class);



 startActivity(i);


}











}
