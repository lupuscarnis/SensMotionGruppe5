package com.example.nicolai.sensmotiongruppe5;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Help_Password_Activity extends AppCompatActivity {

    private TextView writtenText ;
    private ImageButton play,pause,read;
    private Button next;
    private MediaPlayer mp;
    private EditText input;
    private ImageButton setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_password);


    }
}
