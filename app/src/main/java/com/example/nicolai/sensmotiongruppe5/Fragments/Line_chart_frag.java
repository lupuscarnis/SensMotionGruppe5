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

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


public class Line_chart_frag extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private LineChartView lineChartView;
    private IChild_OnFragmentInteractionListener mListener;
    private View view;
    public Line_chart_frag() {

    }

    public static Line_chart_frag newInstance(String message) {
        Line_chart_frag fragment = new Line_chart_frag();
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
        lineChartView = view.findViewById(R.id.chart);


        //initialize the data for X-Axis.
        String[] xData = {"Siddende", "Gående", "I bevægelse", "liggende"};

        //initialize the data for Y-Axis.
        int[] yData = {50, 20, 15, 30};

        //declare List
        List y = new ArrayList();
        List x = new ArrayList();

        //Declare and initialize y line
        Line line = new Line(y).setColor(Color.parseColor("#9C27B0"));


        //add x and y data inside yValues and xValues lists.
        for (int i = 0; i < xData.length; i++) {
            x.add(i, new AxisValue(i).setLabel(xData[i]));
        }

        for (int i = 0; i < yData.length; i++) {
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
