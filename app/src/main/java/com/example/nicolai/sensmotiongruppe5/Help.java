package com.example.nicolai.sensmotiongruppe5;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Help extends AppCompatActivity implements View.OnClickListener {

    TextView writtenText ;
    ImageButton play,pause,read;
    Button next;
    MediaPlayer mp;
    EditText input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        next = findViewById(R.id.help_next);
        read = findViewById(R.id.help_read);
        writtenText = findViewById(R.id.help_textbox);
        input = findViewById(R.id.help_input);
        








    }


    @Override
    public void onClick(View v) {

    }
}
