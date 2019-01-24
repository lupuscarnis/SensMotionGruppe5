package com.example.nicolai.sensmotiongruppe5.Fragments.Nested_Fragments;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;
import com.example.nicolai.sensmotiongruppe5.BLL.LineChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineChartView = view.findViewById(R.id.chart);
        final TextView loading = view.findViewById(R.id.loading_linechart);
        loading.setVisibility(View.VISIBLE);

        new GetDataLine(new GetDataLine.AsyncResponse() {

            @Override
            public void processFinish(ArrayList<String> Dates, ArrayList list, int[] values) {

                //initialize the data for Y-Axis.
                int[] yData = values;

                ArrayList<int[]> valuesOfLines = list;
                //declare List
                List y = new ArrayList();
                List x = new ArrayList();
                List r = new ArrayList();
                List s = new ArrayList();
                List w = new ArrayList();
                List e = new ArrayList();
                List c = new ArrayList();
                List o = new ArrayList();
                List no = new ArrayList();
                //Declare and initialize y line
                for (int p = 0; p < valuesOfLines.get(1).length; p++) {

                    r.add(new PointValue(p, valuesOfLines.get(0)[p]));
                    s.add(new PointValue(p, valuesOfLines.get(1)[p]));
                    w.add(new PointValue(p, valuesOfLines.get(2)[p]));
                    e.add(new PointValue(p, valuesOfLines.get(3)[p]));
                    c.add(new PointValue(p, valuesOfLines.get(4)[p]));
                    o.add(new PointValue(p, valuesOfLines.get(5)[p]));
                    no.add(new PointValue(p, valuesOfLines.get(6)[p]));

                }
                String[] date = new String[Dates.size()];
                int g = 0;
                for (String mb : Dates) {
                    date[g] = mb;
                    g++;

                }

                String[] xDates = formatString(date);
                //add x and y data inside yValues and xValues lists.
                for (int i = 0; i < date.length; i++) {
                    x.add(i, new AxisValue(i).setLabel(xDates[i]));
                }

                for (int i = 0; i < yData.length; i++) {
                    y.add(new AxisValue(yData[i]));
                }

                //This list will hold the line of the graph chart.
                List lines = new ArrayList();
                //resting
                lines.add(new Line(r).setColor(Color.BLUE));
                //standing
                lines.add(new Line(s).setColor(Color.GRAY));
                //walking
                lines.add(new Line(w).setColor(Color.RED));
                //exercise
                lines.add(new Line(e).setColor(Color.parseColor("#FFD700")));
                //cycling
                lines.add(new Line(c).setColor(Color.parseColor("#006400")));
                //Other
                lines.add(new Line(o).setColor(Color.BLACK));
                //no data
                lines.add(new Line(no).setColor(Color.parseColor("#9932CC")));


                //add graph line to the overall data chart.
                LineChartData data = new LineChartData();
                data.setLines(lines);


                //Show x values in the line chart graph.
                Axis axis = new Axis();
                axis.setValues(x);
                axis.setTextColor(Color.parseColor("#03A9F4"));
                data.setAxisXBottom(axis);
                //Show y values in the line chart graph.
                Axis yAxis = new Axis();
                yAxis.setValues(y);
                yAxis.setTextColor(Color.parseColor("#03A9F4"));
                yAxis.setTextSize(7);
                data.setAxisYLeft(yAxis);
                lineChartView.setLineChartData(data);
                loading.setVisibility(View.INVISIBLE);
                lineChartView.invalidate();


            }
        }).execute();
        //initialize the data for X-Axis.



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

    private String[] formatString(String[] Array) {
        String[] s = new String[7];
        for (int i = 0; i < Array.length; i++) {

            s[i] = Array[i].substring(0, 5);

        }


        return s;
    }
}

class GetDataLine extends AsyncTask<Void, Void, Void> {
    public AsyncResponse delegate = null;
    private ArrayList<String> fa;
    private ArrayList<int[]> list;
    private int[] values;

    public GetDataLine(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Void doInBackground(Void... Void) {

        IData ed = new DAOHandler();
        IChart b = new LineChart();
        fa = ed.getIntervalDates();
        list = ((LineChart) b).getLineValues();
        values = ((LineChart) b).getValueArray();


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        delegate.processFinish(fa, list, values);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }


    public interface AsyncResponse {
        void processFinish(ArrayList<String> Dates, ArrayList list, int[] values);
    }
}
