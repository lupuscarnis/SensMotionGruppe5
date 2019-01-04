package com.example.nicolai.sensmotiongruppe5;

import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolai.sensmotiongruppe5.BLL.DAO;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Min_Side_Activity extends Fragment implements View.OnClickListener{

    ImageButton indstil;
     Button help, actualHelp;
    // DAO singleton instance object
    DAO userDAO = DAO.getInstance();
    // For storing the values from JSON
    List<List<String>> valuesArray;

    public int helpCounter = 0;
    String dialogueMessage = "here is some nice help";
    int dialogImage = R.drawable.setting;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
View rootView = inflater.inflate(R.layout.activity_min_side, container, false);
            // Inflate the layout for this fragment

            new GetJSON().execute();
         help = rootView.findViewById(R.id.Min_side_help);
         help.setOnClickListener(this);

         actualHelp = rootView.findViewById(R.id.HelpBut);
         actualHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onHelp(view);
            }
        });
        return rootView;
    }

    public void openSettingActivity(){
        Intent intent = new Intent(getActivity(), Setting_Activity.class);
        startActivity(intent);
    }


    /*
     * Download JSON as ASYNCTASK
     * Prompts to screen
     */

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Toast.makeText(getActivity(),"Json Data is downloading",Toast.LENGTH_LONG).show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

            //userDAO.getData();
            valuesArray = userDAO.getData();
            return null;

        }
        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            // Save JSON_STRING to phone
            SharedPreferences.Editor prefEditor = getDefaultSharedPreferences(getActivity()).edit();
            prefEditor.putString("JSON_STRING", valuesArray.toString()).apply();

            // Retrieve JSON_STRING from phone
            //String jsonString = PreferenceManager.getDefaultSharedPreferences(Min_Side_Activity.this).getString("JSON_STRING", "DefaultStringIfNULL");
            //Log.d("Debug SharedPreferences",""+jsonString);

            // Toast when done downloading/parsing JSON
            Toast.makeText(getActivity(),"Json Data example info "+valuesArray.get(0).get(1),Toast.LENGTH_LONG).show();
        }
    }
public void onClick(View v){

    Intent   i = new Intent(getActivity(), Min_Data_Activity.class);
    startActivity(i);

}

    public void onHelp (final View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view1;    view1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_dialog_picture, null);
        TextView title = (TextView) view1.findViewById(R.id.title);
        ImageButton imageButton = (ImageButton) view1.findViewById(R.id.image);
        title.setText("i'm here to help ");
        builder.setMessage(dialogueMessage);
        view1.findViewById(R.id.dialogTv);
        imageButton.setImageResource(dialogImage);
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override        public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(Min_Side_Activity.this, "Next", Toast.LENGTH_SHORT).show();

                View vew = view;

                switch (helpCounter){
                    case 0:
                        dialogueMessage = " hjaelp nummer 2";
                        dialogImage = R.drawable.star2;
                        onHelp(vew);
                        helpCounter = -1;
                        break;

                    case 1:
                        dialogueMessage = "hjaelp nummer 3 ";

                        onHelp(vew);
                        helpCounter = 2;
                        break;

                    case 2:
                        onHelp(vew);
                        helpCounter = -0;
                }



            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override        public void onClick(DialogInterface dialogInterface, int i) {
              //  Toast.makeText(Min_Side_Activity.this, "Cancel", Toast.LENGTH_SHORT).show();
                helpCounter = 0;
            }
        });
        builder.setView(view1);    builder.show();




    }






}
