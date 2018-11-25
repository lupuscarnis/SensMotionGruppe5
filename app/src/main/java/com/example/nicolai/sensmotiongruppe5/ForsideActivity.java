package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nicolai.sensmotiongruppe5.BLL.DAO;

public class ForsideActivity extends AppCompatActivity {

    ImageButton indstil;

    // DAO singleton instance object
    DAO userDAO = DAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        new GetJSON().execute();

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

    /**
     * Download JSON as ASYNCTASK
     * Prompts to screen
     */

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Toast.makeText(ForsideActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        protected Void doInBackground(Void... arg0) {

            userDAO.getData();
            return null;

        }
    }



    public void helped(View v){
     Intent   i = new Intent(this, Help_Activity.class);

 startActivity(i);


}











}
