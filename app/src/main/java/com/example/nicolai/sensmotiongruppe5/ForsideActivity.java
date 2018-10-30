package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ForsideActivity extends AppCompatActivity {

    ImageButton indstil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        // Launch Login Activity. Use "@" in email to get past it.
        startActivity(new Intent(ForsideActivity.this, LoginActivity.class));


        indstil = findViewById(R.id.forside_ind);
        indstil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingActivity();
            }
        });
    }

    public void openSettingActivity(){
        Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
    }



public void helped(View v){
     Intent   i = new Intent(this, HelpActivity.class);

 startActivity(i);


}











}