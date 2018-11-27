package com.example.nicolai.sensmotiongruppe5.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;



public class Date_Slider_frag extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private LineChartView lineChartView;
    public Date_Slider_frag() {

    }

    public static Date_Slider_frag newInstance(String message) {
        Date_Slider_frag fragment = new Date_Slider_frag();
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
        return inflater.inflate(R.layout.fragment_date__slider_frag, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lineChartView = getView().findViewById(R.id.chart);


        //initialize the data for X-Axis.
        String[] xData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
                "Oct", "Nov", "Dec"};

        //initialize the data for Y-Axis.
        int[] yData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 80, 18};

        //declare List
        List y = new ArrayList();
        List x = new ArrayList();

        //Declare and initialize y line
        Line line = new Line(y).setColor(Color.parseColor("#9C27B0"));




        //add x and y data inside yValues and xValues lists.
        for(int i = 0; i < xData.length; i++){
            x.add(i, new AxisValue(i).setLabel(xData[i]));
        }

        for (int i = 0; i < yData.length; i++){
            y.add(new PointValue(i, yData[i]));
        }

        //This list will hold the line of the graph chart.
        List lines = new ArrayList();
        lines.add(line);


        //add graph line to the overall data chart.
        LineChartData data = new LineChartData();
        data.setLines(lines);

        lineChartView.setLineChartData(data);

        //Show x values in the line chart graph.
        Axis axis = new Axis();
        axis.setValues(x);
        data.setAxisXBottom(axis);
        axis.setTextColor(Color.parseColor("#03A9F4"));



        //Show y values in the line chart graph.
        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);


    }

}
