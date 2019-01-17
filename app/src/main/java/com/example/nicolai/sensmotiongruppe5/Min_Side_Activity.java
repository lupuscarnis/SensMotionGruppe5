package com.example.nicolai.sensmotiongruppe5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.nicolai.sensmotiongruppe5.Fragments.Text_fragment;
import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.Rute.Rute;
import com.example.nicolai.sensmotiongruppe5.Rute.Rutevector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        run = rootView.findViewById(R.id.button_running);
        cycling = rootView.findViewById(R.id.button_bike);
        cycling.setOnClickListener(this);
        run.setOnClickListener(this);
        walk.setOnClickListener(this);

        ArrayList<Rutevector> ruteVectorsList = new ArrayList<>();
        Rutevector ruteVector = new Rutevector();

        ruteVector.setStartX(50);
        ruteVector.setStartY(60);
        ruteVector.setEndX(400);
        ruteVector.setEndY(60);
        ruteVectorsList.add(ruteVector);

        Rutevector steve = new Rutevector();
        steve.setStartX(400);
        steve.setStartY(60);
        steve.setEndX(400);
        steve.setEndY(200);
        ruteVectorsList.add(steve);


        // Inflate the layout for this fragment

        new GetJSON().execute();

        hello = new Rute(rootView.findViewById(R.id.canvas_rute), ruteVectorsList);


        Achieve_Activity.completed(0);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Begin the transaction
        Fragment childFragment = new Text_fragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.text_fragment, childFragment).commit();




    }


    @Override
    public void onClick(View v) {
        int[] values = {0, 0, 0, 0};

        if (v == walk) {
            values[0] = 5;
            hello.drawRute(values);
        }
        if (v == run) {
            hello.drawRute(values);
        }
        if (v == cycling) {
            values[2] = 10;
            hello.drawRute(values);
        }


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





