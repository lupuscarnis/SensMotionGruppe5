package com.example.nicolai.sensmotiongruppe5.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;



public class Bar_graph_frag extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";




    public Bar_graph_frag() {

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bar_gargh_frag, container, false);
    }

    BarChart barChart;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        barChart = getView().findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(88f,1));
        barEntries.add(new BarEntry(66f,2));
        barEntries.add(new BarEntry(12f,3));
        BarDataSet barDataSet = new BarDataSet (barEntries, "Activity");


        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Liggende");
        theDates.add("I bævegelse");
        theDates.add("Gående");
        theDates.add("Siddende");


        BarData theData = new BarData(theDates,barDataSet);


        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }


}
