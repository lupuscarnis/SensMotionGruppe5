package com.example.nicolai.sensmotiongruppe5;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;

import java.util.Arrays;
import java.util.List;

public class Min_Side_Activity extends Fragment {

    View rootView;
    Rute_Canvas hello;
    // Sets the default keys for the logged in patient
    DAOHandler daoHandler = new DAOHandler("k5W2uX", "6rT39u");

    // For storing the values from JSON
    public List<List<String>> valuesArray;
    public String[] allDates;

    public int helpCounter = 0;
    String dialogueMessage = "here is some nice help";
    int dialogImage = R.drawable.setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                         Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_min_side, drawer_layout, false);
        }
            // Inflate the layout for this fragment

         new GetJSON().execute();
        hello = rootView.findViewById(R.id.canvas_rute);
        // P
        hello.Draw(50, 50, 50, 300);
        hello.Draw(50, 50, 100, 100);
        hello.Draw(100, 100, 50, 200);
        // I
        hello.Draw(200, 50, 200, 75);
        hello.Draw(200, 100, 200, 300);

        // K
        hello.Draw(300, 50, 300, 300);
        hello.Draw(300, 150, 350, 50);
        hello.Draw(300, 150, 350, 300);

        //Circle

        hello.DrawCircle(400, 250, 20);

        return rootView;
    }




    /*
     * Download JSON as ASYNCTASK
     * Prompts to screen
     */

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            //Toast.makeText(getActivity(),"Json Data is downloading",Toast.LENGTH_LONG).show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

            //valuesArray = daoHandler.getAllInfo(7);
            allDates = daoHandler.getAllDates();
            return null;

        }
        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            // Save JSON_STRING to phone
            //SharedPreferences.Editor prefEditor = getDefaultSharedPreferences(getActivity()).edit();
            //prefEditor.putString("JSON_STRING", valuesArray.toString()).apply();

            // Retrieve JSON_STRING from phone
            //String jsonString = PreferenceManager.getDefaultSharedPreferences(Min_Side_Activity.this).getString("JSON_STRING", "DefaultStringIfNULL");
            //Log.d("Debug SharedPreferences",""+jsonString);

            // Toast when done downloading/parsing JSON
            //Toast.makeText(getActivity(),"Json Data example info "+valuesArray.get(0).get(1),Toast.LENGTH_LONG).show();

            // Toast all dates from JSON
            String datesArr = Arrays.toString(allDates);
            Toast.makeText(getActivity(),"Json Data example info "+datesArr,Toast.LENGTH_LONG).show();

        }
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
            @Override public void onClick(DialogInterface dialogInterface, int i) {
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
