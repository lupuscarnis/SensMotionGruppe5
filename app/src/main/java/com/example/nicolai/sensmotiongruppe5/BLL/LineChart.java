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
        int[] resting = new int[7];
        int[] standing = new int[7];
        int[] walking = new int[7];
        int[] exercise = new int[7];
        int[] cycling = new int[7];
        int[] other = new int[7];
        int[] noData = new int[7];


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
        s[1] = 1000;
        s[2] = 700;
        s[3] = 400;
        s[4] = 200;
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
