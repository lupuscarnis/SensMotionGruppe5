package com.example.nicolai.sensmotiongruppe5.Fragments.Nested_Fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.BLL.BarGraph;
import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;


public class Bar_graph_frag extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private View view;
    private IChild_OnFragmentInteractionListener mListener;


    public Bar_graph_frag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_gargh_frag, container, false);
        return view;
    }


    public static Bar_graph_frag newInstance(String message) {
        Bar_graph_frag fragment = new Bar_graph_frag();
        Bundle args = new Bundle();
        args.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    BarChart barChart;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IChart chart = new BarGraph();
        int[] data = chart.getData();
        barChart = view.findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry((float) data[0], 0));
        barEntries.add(new BarEntry((float) data[1], 1));
        barEntries.add(new BarEntry((float) data[2], 2));
        barEntries.add(new BarEntry((float) data[3], 3));
        barEntries.add(new BarEntry((float) data[4], 3));
        barEntries.add(new BarEntry((float) data[5], 3));
        barEntries.add(new BarEntry((float) data[6], 3));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Activity");


        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("I ro");
        theDates.add("Stående");
        theDates.add("Gående");
        theDates.add("Exercice");
        theDates.add("Cyklende");
        theDates.add("Other");
        theDates.add("Ingen Data");

        BarData theData = new BarData(theDates, barDataSet);


        barChart.setData(theData);


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
