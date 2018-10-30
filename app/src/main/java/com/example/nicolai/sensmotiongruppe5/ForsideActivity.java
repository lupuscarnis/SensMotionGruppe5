package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ForsideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        // Launch Login Activity. Use "@" in email to get past it.
        startActivity(new Intent(ForsideActivity.this, LoginActivity.class));
        //Alan

    }



public void helped(View v){
     Intent   i = new Intent(this, HelpActivity.class);



 startActivity(i);


}











}
