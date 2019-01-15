package com.example.nicolai.sensmotiongruppe5.BLL;

import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

public class PieChart implements IChart {


    @Override
    public int[] getData() {

        int[] array = new int[7];
        IData data = new DAOHandler();
        ArrayList<JSONData> s;
        int sum = 0;

        s = data.getCurrentSelectDatesDataAsObject();

        for (JSONData currentData : s) {

            array[0] += (int) currentData.getResting();
            array[1] += (int) currentData.getStanding();
            array[2] += (int) currentData.getWalking();
            array[3] += (int) currentData.getExercise();
            array[4] += (int) currentData.getCycling();
            array[5] += (int) currentData.getOtherd();
            array[6] += (int) currentData.getNodata();

            sum += 1440;
        }

        array[0] = array[0] / sum;
        array[1] = array[1] / sum;
        array[2] = array[2] / sum;
        array[3] = array[3] / sum;
        array[4] = array[4] / sum;
        array[5] = array[5] / sum;
        array[6] = array[6] / sum;

        return array;

    }

    @Override
    public void update() {

    }
}
