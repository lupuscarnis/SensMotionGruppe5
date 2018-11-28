package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nicolai.sensmotiongruppe5.BLL.DAO;

public class Min_Side_Activity extends AppCompatActivity implements View.OnClickListener{

    ImageButton indstil;
    Button help;
    // DAO singleton instance object
    DAO userDAO = DAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_side);

        new GetJSON().execute();
        help = findViewById(R.id.Min_side_help);
        help.setOnClickListener(this);


    }


    /**
     * Download JSON as ASYNCTASK
     * Prompts to screen
     */

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Toast.makeText(Min_Side_Activity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        protected Void doInBackground(Void... arg0) {

            userDAO.getData();
            return null;

        }
    }
public void onClick(View v){
    Intent   i = new Intent(this, Min_Data_Activity.class);
    startActivity(i);

}




}
