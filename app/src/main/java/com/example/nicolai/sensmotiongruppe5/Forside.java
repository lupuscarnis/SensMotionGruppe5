package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.support.v4.content.ContextCompat.startActivity;

public class Forside extends AppCompatActivity {








public void helped(View v){
     Intent   i = new Intent(this, Help.class);



 startActivity(i);


}











}
