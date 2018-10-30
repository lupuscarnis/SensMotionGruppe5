package com.example.nicolai.sensmotiongruppe5;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

    public class Help extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

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
        mp =  MediaPlayer.create(getApplicationContext(), R.raw.bruger_id);
        mp.setOnCompletionListener(this);








    }
    @Override
    protected void onStop() {
        super.onStop();

        if(mp != null){
            mp.release();
        }

    }
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
        if(v == next){

            setContentView(R.layout.activity_main);

    }

        if(v == read){
            read.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            mp.start();



        }
        if(v == pause)
        {
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            mp.pause();
        }
        if(v == play)
        {
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);

        }


    }

    public void onCompletion(MediaPlayer arg0)
    {

        mp.reset();
        read.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);


    }

}
