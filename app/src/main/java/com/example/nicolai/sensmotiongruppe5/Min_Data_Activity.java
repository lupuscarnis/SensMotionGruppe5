package com.example.nicolai.sensmotiongruppe5;

import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nicolai.sensmotiongruppe5.Fragments.Date_Slider_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Min_Data_frag;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Min_Data_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min__data);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.place_holder_date, Date_Slider_frag.newInstance());
        ft.commit();


        FragmentTransaction fb = getSupportFragmentManager().beginTransaction();
        fb.replace(R.id.place_holder_graf, Min_Data_frag.newInstance());
        fb.commit();
        PieChartView pieChartView = findViewById(R.id.chart);

        List<SliceValue> pieData = new ArrayList<>();




        pieData.add(new SliceValue(15, Color.BLUE).setLabel("Q1: $10"));
        pieData.add(new SliceValue(25, Color.GRAY).setLabel("Q2: $4"));
        pieData.add(new SliceValue(10, Color.RED).setLabel("Q3: $18"));
        pieData.add(new SliceValue(60, Color.MAGENTA).setLabel("Q4: $28"));




        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartView.setPieChartData(pieChartData);
    }
}
