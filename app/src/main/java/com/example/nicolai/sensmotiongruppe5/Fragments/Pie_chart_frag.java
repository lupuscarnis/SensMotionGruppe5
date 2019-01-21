package com.example.nicolai.sensmotiongruppe5.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.BLL.PieChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.R;
import com.example.nicolai.sensmotiongruppe5.Tests.LiePie;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        pieChartView = view.findViewById(R.id.pieChart);
        IChart chart = new LiePie();
        int[] data = chart.getData();
        new GetDataPie(new GetDataPie.AsyncResponse() {
            @Override
            public void processFinish(int[] values) {

                List<SliceValue> pieData = new ArrayList<>();

                pieData.add(new SliceValue(values[0], Color.BLUE).setLabel(""));
                pieData.add(new SliceValue(values[1], Color.GRAY).setLabel(""));
                pieData.add(new SliceValue(values[2], Color.RED).setLabel(""));
                pieData.add(new SliceValue(values[3], Color.BLACK).setLabel(""));
                pieData.add(new SliceValue(values[4], Color.parseColor("#006400")).setLabel(""));
                pieData.add(new SliceValue(values[5], Color.parseColor("#FFD700")).setLabel(""));
                pieData.add(new SliceValue(values[6], Color.parseColor("#9932CC")).setLabel(""));


                PieChartData pieChartData = new PieChartData(pieData);
                pieChartData.setHasCenterCircle(true).setCenterText1("SENS motion").setCenterText1FontSize(17).setCenterText1Color(Color.parseColor("#0097A7"));
                pieChartData.setHasLabels(true);
                pieChartView.setPieChartData(pieChartData);
                pieChartView.invalidate();
            }
        }).execute();





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

class GetDataPie extends AsyncTask<Void, Void, Void> {
    public AsyncResponse delegate = null;
    private int[] values;

    public GetDataPie(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Void doInBackground(Void... Void) {

        IChart chart = new PieChart();
        values = chart.getData();


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        delegate.processFinish(values);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }


    public interface AsyncResponse {
        void processFinish(int[] values);
    }
}
