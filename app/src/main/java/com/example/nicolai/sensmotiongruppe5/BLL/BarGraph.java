package com.example.nicolai.sensmotiongruppe5.BLL;

import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

public class BarGraph implements IChart {
    @Override
    public int[] getData() {

        int[] array = new int[7];
        IData data = new DAOHandler();
        ArrayList<JSONData> s;
        s = data.getAllInfoAsObjects();


        for (JSONData currentData : s) {


            array[0] += (int) currentData.getResting();
            array[1] += (int) currentData.getStanding();
            array[2] += (int) currentData.getWalking();
            array[3] += (int) currentData.getExercise();
            array[4] += (int) currentData.getCycling();
            array[5] += (int) currentData.getOtherd();
            array[6] += (int) currentData.getNodata();
        }


        return array;
    }

    @Override
    public void update() {

    }
}
