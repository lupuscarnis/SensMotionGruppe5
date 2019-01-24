package com.example.nicolai.sensmotiongruppe5;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Activities.Login_Activity;

public class Help_Activity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private TextView writtenText;
    private ImageButton play, pause, read;
    private Button next, help_return;
    private MediaPlayer mp;
    private EditText input;
    private ImageButton setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_username);

        // Finds all the Elements that can be manipulateted
        help_return = findViewById(R.id.help_return);
        next = findViewById(R.id.help_next);
        read = findViewById(R.id.help_read);
        pause = findViewById(R.id.help_pause);
        play = findViewById(R.id.help_play);
        writtenText = findViewById(R.id.help_textbox);
        input = findViewById(R.id.help_input);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.bruger_id);


        //Sets the Text on screen
        writtenText.setText(R.string.text_user_login_help);
        next.setText(R.string.text_user_login_help_button);
        help_return.setText(R.string.text_user_return_button);


        //Sets all the Listnerens For this activity
        help_return.setOnClickListener(this);
        read.setOnClickListener(this);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        mp.setOnCompletionListener(this);


    }

    // Releases the MediaPlayer
    @Override
    protected void onStop() {
        super.onStop();

        if (mp != null) {
            mp.release();
        }
        this.finish();

    }

    // Releases the  MediaPlayer
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
        }


    }


    @Override
    public void onClick(View v) {
        // enters the settings menu
        if (v == setting) {
            openSetting(v);

            //Intent i = new Intent(Help_Activity.this, Setting_Activity.class);
            //startActivity(i);
        }
        // goes to the next screen or returns to login
        if (v == next) {
            if (input.getText().toString().isEmpty()) {
                input.setHint("Du skal skrive et brugernavn");
            } else {
                if (mp != null) {
                    mp.release();
                }
                mp = MediaPlayer.create(getApplicationContext(), R.raw.plan_with_man);
                mp.setOnCompletionListener(this);
                writtenText.setText(R.string.text_user_password);
                //input.getText().toString();
                input.setText("", TextView.BufferType.EDITABLE);
                input.setHint("Password");
                next.setVisibility(View.INVISIBLE);
                help_return.setVisibility(View.VISIBLE);

            }
        }
        //After password has been inserted return.
        if (v == help_return) {
            Intent i = new Intent(Help_Activity.this, Login_Activity.class);
            startActivity(i);

        }


        //starts the Mp3 file
        if (v == read) {
            read.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);
            mp.start();


        }
        //pause the MP3 file
        if (v == pause) {
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.INVISIBLE);
            mp.pause();
        }
        // Resumes the MP3 song
        if (v == play) {
            mp.start();
            play.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);

        }


    }

    public void onCompletion(MediaPlayer arg0) {

        read.setVisibility(View.VISIBLE);
        pause.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);


    }

    public void openSetting(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Settings");
        alertDialogBuilder.setMessage("Settings go right here");
        alertDialogBuilder.setPositiveButton("Gæm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        alertDialogBuilder.setNegativeButton("Anuller", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}