package com.example.nicolai.sensmotiongruppe5.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class Pie_chart_frag extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private PieChartView pieChartView;
    private IChild_OnFragmentInteractionListener mListener;
    private View view;
    public Pie_chart_frag() {

    }


    public static Pie_chart_frag newInstance(String message) {
        Pie_chart_frag fragment = new Pie_chart_frag();
        Bundle args = new Bundle();
        args.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pieChartView = getView().findViewById(R.id.pieChart);

        List<SliceValue> pieData = new ArrayList<>();


        pieData.add(new SliceValue(15, Color.BLUE).setLabel("Siddende"));
        pieData.add(new SliceValue(25, Color.GRAY).setLabel("Gående"));
        pieData.add(new SliceValue(10, Color.RED).setLabel("I bevægelse"));
        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel(" Liggende"));


        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartView.setPieChartData(pieChartData);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IChild_OnFragmentInteractionListener) {
            mListener = (IChild_OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromChildFragment(Uri uri);
    }


}
