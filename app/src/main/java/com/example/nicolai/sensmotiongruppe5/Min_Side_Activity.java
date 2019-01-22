package com.example.nicolai.sensmotiongruppe5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;
import com.example.nicolai.sensmotiongruppe5.BLL.JSONData;
import com.example.nicolai.sensmotiongruppe5.Interface.IHighlight;
import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.Rute.Quiz_Highlight;
import com.example.nicolai.sensmotiongruppe5.Rute.Rute;
import com.example.nicolai.sensmotiongruppe5.Rute.Rutevector;
import com.example.nicolai.sensmotiongruppe5.Rute.Text_Highlight;

import java.util.ArrayList;

public class Min_Side_Activity extends Fragment implements View.OnClickListener {

    View rootView;
    private IParent_OnFragmentInteractionListener mListener;


    // Sets the default keys for the logged in patient
    DAOHandler daoHandler = new DAOHandler();

    // For storing the values from JSON
    public String[] allDates;
    public String numSteps;
    ArrayList<JSONData> dataArray = new ArrayList<JSONData>();
    private Button walk, run, cycling;
    public int helpCounter = 0;
    String dialogueMessage = "here is some nice help";
    int dialogImage = R.drawable.setting;
    Rute hello;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_min_side, drawer_layout, false);
        }

        getActivity().startService(new Intent(getActivity(), backgroundService.class));
        walk = rootView.findViewById(R.id.button_walk);

        walk.setOnClickListener(this);


        new GetJSON().execute();

        Achieve_Activity.completed(0);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        // creates the rute and its shit
        ArrayList<Rutevector> ruteVectorsList = new ArrayList<>();
        Rutevector ruteVector = new Rutevector(50, 60, 300, 60);
        ruteVectorsList.add(ruteVector);
        Rutevector steve = new Rutevector(300, 60, 300, 200);
        ruteVectorsList.add(steve);

        Rutevector dave = new Rutevector(300, 200, 200, 100);
        ruteVectorsList.add(dave);
        ArrayList<IHighlight> textHighlights = new ArrayList<>();
        Text_Highlight start = new Text_Highlight(50, 60, 10, "start");
        start.setText("A new Beginning");
        Quiz_Highlight middle = new Quiz_Highlight(300, 60, 10, "middle", "Whats a mix between Bear and Deer");
        ArrayList<String> s = new ArrayList();
        s.add("Dear");
        s.add("Beer");
        s.add("BearDear");
        s.add("Non of the above");
        middle.setAnswers(s);

        Text_Highlight end = new Text_Highlight(300, 200, 10, "end");
        end.setText("I See dead People!!!!!");
        Quiz_Highlight secret = new Quiz_Highlight(200, 100, 10, "secret", "Do i look fat in this ?");
        ArrayList<String> a = new ArrayList();
        a.add("Yes ");
        a.add("Yes really fat");
        a.add("Wow it still \"fits\" you !?");
        a.add("Bitch Please!");
        secret.setAnswers(a);
        textHighlights.add(start);
        textHighlights.add(middle);
        textHighlights.add(end);
        textHighlights.add(secret);

        // the end of rute creation

        int[] values = {1, 0, 0, 0};
        hello = new Rute(view.findViewById(R.id.canvas_rute), ruteVectorsList, textHighlights, getChildFragmentManager(), getParentFragment());
        hello.draw(values, 0f);

    }


    @Override
    public void onClick(View v) {
        int[] values = {0, 0, 0, 0};
            values[0] = 5;
        hello.draw(values, 0);


    }

    /*
     * Download JSON as ASYNCTASK
     * Prompts to screen
     */

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IParent_OnFragmentInteractionListener) {
            mListener = (IParent_OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // https://stackoverflow.com/questions/6672066/fragment-inside-fragment // how i solved the issue of mutiple fragments
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromParentFragment(Uri uri);
    }

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Toast.makeText(getActivity(),"Updating data...",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            //valuesArray = daoHandler.getAllInfo(7);
            /*dataArray = daoHandler.getCurrentSelectDatesDataAsObject();
            allDates = daoHandler.getAllDates();
            numSteps = daoHandler.getActivityByDate("08-01-2019", "steps");*/
            //dataArray = daoHandler.getCurrentSelectDatesDataAsObject();
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            Toast.makeText(getActivity(),"Update done...",Toast.LENGTH_LONG).show();

            // For debugging
            //Toast.makeText(getActivity(), "Dates: " + Arrays.toString(allDates) +"", Toast.LENGTH_LONG).show();
            //Toast.makeText(getActivity(), "Steps: " + numSteps +"", Toast.LENGTH_LONG).show();
            //Toast.makeText(getActivity(), "DAO Dates: " + Arrays.toString(daoHandler.getDAOCurrentDates()) +"", Toast.LENGTH_LONG).show();
            //testArray = daoHandler.getCurrentSelectDatesDataAsObject();
            //Toast.makeText(getActivity(), "DAO interval Data: " + daoHandler.getCurrentSelectDatesDataAsObject().get(0).getStartDate() +"", Toast.LENGTH_LONG).show();
            //Toast.makeText(getActivity(), "DAO interval Data: " + dataArray.size() +"", Toast.LENGTH_LONG).show();
            //Toast.makeText(getActivity(), "DAO interval Data: " + dataArray.size() +"", Toast.LENGTH_LONG).show();

        }
    }

}





