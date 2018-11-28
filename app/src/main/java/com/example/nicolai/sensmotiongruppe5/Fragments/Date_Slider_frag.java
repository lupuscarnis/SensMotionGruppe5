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
        lineChartView = getView().findViewById(R.id.lineChart);

        List<PointValue> scatter = new ArrayList<>();


        scatter.add(new PointValue(13, 14));
        scatter.add(new PointValue(15, 14));
        scatter.add(new PointValue(16, 14));
        scatter.add(new PointValue(17, 14));

        Line line = new Line(scatter).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<>(1);
        lines.add(line);
        LineChartData data = new LineChartData(lines);

        lineChartView.setLineChartData(data);


    }

}
