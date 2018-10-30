package com.example.nicolai.sensmotiongruppe5;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;


public class HelpActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    TextView writtenText ;
    ImageButton play,pause,read;
    Button next;
    MediaPlayer mp;
    EditText input;
    ImageButton setting;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // finds all the Elements that can be manipulateted
        next = findViewById(R.id.help_next);
        read = findViewById(R.id.help_read);
        pause = findViewById(R.id.help_pause);
        play = findViewById(R.id.help_play);
        writtenText = findViewById(R.id.help_textbox);
        input = findViewById(R.id.help_input);
        mp =  MediaPlayer.create(getApplicationContext(), R.raw.bruger_id);
        setting = findViewById(R.id.indstillinger);

        //Sets all the Listnerens For this activity
        read.setOnClickListener(this);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        setting.setOnClickListener(this);
        mp.setOnCompletionListener(this);



    }
    // Releases the MediaPlayer
    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
        if(mp != null){
            mp.release();
        }

    }
    // Releases the  MediaPlayer
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mp.stop();
        if(mp != null){
            mp.release();
        }



    }



    @Override
    public void onClick(View v) {
        // enters the settings menu
        if(v == setting)
        {
            Intent i = new Intent(HelpActivity.this,SettingActivity.class);
            startActivity(i);
         }
         // goes to the next screen or returns to login
        if(v == next){
            Intent i = new Intent(HelpActivity.this, LoginActivity.class);
            startActivity(i);
            }
    //starts the Mp3 file
        if(v == read){
            read.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            mp.start();



        }
        //pause the MP3 file
        if(v == pause)
        {
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            mp.pause();
        }
        // Resumes the MP3 song
        if(v == play)
        {
            mp.start();
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);

        }
        



    }

    public void onCompletion(MediaPlayer arg0)
    {

        mp.reset();
        read.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);
        play.setVisibility(View.GONE);


    }

}
