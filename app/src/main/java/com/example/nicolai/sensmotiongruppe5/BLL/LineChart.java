package com.example.nicolai.sensmotiongruppe5.BLL;

import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

public class LineChart implements IChart {

    private int sum = 0;


    public ArrayList<int[]> getLineValues() {
        ArrayList op = new ArrayList<int[]>();
        IData data = new DAOHandler();
        ArrayList<JSONData> s;
        s = data.getCurrentSelectDatesDataAsObject();

        int i = 0;
        int[] resting = new int[s.size()];
        int[] standing = new int[s.size()];
        int[] walking = new int[s.size()];
        int[] exercise = new int[s.size()];
        int[] cycling = new int[s.size()];
        int[] other = new int[s.size()];
        int[] noData = new int[s.size()];


        for (JSONData currentData : s) {


            resting[i] = (int) currentData.getResting();
            standing[i] = (int) currentData.getStanding();
            walking[i] = (int) currentData.getWalking();
            exercise[i] = (int) currentData.getExercise();
            cycling[i] = (int) currentData.getCycling();
            other[i] = (int) currentData.getOtherd();
            noData[i] = (int) currentData.getNodata();
            sum = 1440;
            i++;
        }

        op.add(resting);
        op.add(standing);
        op.add(walking);
        op.add(exercise);
        op.add(cycling);
        op.add(other);
        op.add(noData);


        return op;

    }

    public int[] getValueArray() {
        int[] s = new int[6];

        s[0] = 1440;
        s[1] = 1200;
        s[2] = 900;
        s[3] = 600;
        s[4] = 300;
        s[5] = 0;

        return s;


    }


    @Override
    public int[] getData() {
        return new int[0];
    }

    @Override
    public void update() {

    }
}
