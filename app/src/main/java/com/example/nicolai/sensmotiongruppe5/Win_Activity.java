package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.BLL.SharedPrefs;
import com.luolc.emojirain.EmojiRainLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Win_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private Button share, goBack;
    int n;

    EmojiRainLayout mContainer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity__win );

       n = SharedPrefs.getInt("intKey");
       n = n +1;
       if (n >= 1){
           Achieve_Activity.completed(1);
       }

       if (n >= 3){
            Achieve_Activity.completed(2);
        }
        if (n >= 10){
            Achieve_Activity.completed(3);
        }

        SharedPrefs.saveInt(n, "intKey");







            mContainer = findViewById(R.id.activity_win);
            mContainer.addEmoji(R.drawable.achi);
            mContainer.addEmoji(R.drawable.sens_logo);

            mContainer.stopDropping();
            mContainer.setPer(10);
            mContainer.setDuration(7200);
            mContainer.setDropDuration(2400);
            mContainer.setDropFrequency(500);
            mContainer.startDropping();





            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int)(width*.7), (int)(height*.8));




            textview = findViewById(R.id.text);
            textview.setText("Tillykkee!!!");

            share = findViewById(R.id.share);
            share.setText("Del");

            goBack = findViewById(R.id.goBack);
            goBack.setText("Ny rute");



            share.setOnClickListener(this);
            goBack.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(v == share){
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

                Uri screenshotUri = Uri.parse("android.resource://com.example.nicolai.sensmotiongruppe5/drawable/achi");
                try {
                    InputStream stream = getContentResolver().openInputStream(screenshotUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                sharingIntent.setType("image/jpeg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
               /* Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Du har fuldført achievementen.";
                String shareSub = "Achivement gennemført!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));*/

            }

        }

      /*  @Override
        public void onBackPressed() {
            Intent i  = new Intent(getApplicationContext(), FirstActivity.class);
            startActivity(i);
            finish();
        }*/
    }
