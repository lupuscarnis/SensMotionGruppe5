package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.support.v4.content.ContextCompat.startActivity;

public class Forside extends AppCompatActivity {

    ImageButton indstil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        // Launch Login Activity. Use "@" in email to get past it.
        startActivity(new Intent(Forside.this, LoginActivity.class));

       indstil=findViewById(R.id.forside_ind);
       indstil.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openActivity();
               }
       });

    }


public void helped(View v){
     Intent   i = new Intent(this, Help.class);
 startActivity(i);


}

    private void openActivity() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }




    }

