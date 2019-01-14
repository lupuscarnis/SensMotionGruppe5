package com.example.nicolai.sensmotiongruppe5.BLL;

import com.example.nicolai.sensmotiongruppe5.Interface.IData;
import com.example.nicolai.sensmotiongruppe5.Interface.IPieChart;

import java.util.ArrayList;

public class PieChart implements IPieChart {


    @Override
    public int[] getData() {

        int[] array = new int[4];
        IData data = new DAOHandler();
        ArrayList<JSONData> s;
        int sum = 0;

        s = data.getAllInfoAsObjects();

        for (JSONData currentData : s) {

            array[0] += (int) currentData.getCycling();
            array[1] += (int) currentData.getWalking();
            array[2] += (int) currentData.getExercise();
            array[3] += (int) currentData.getNodata();

            sum += 1440;
        }

        array[0] = array[0] / sum;
        array[1] = array[1] / sum;
        array[2] = array[2] / sum;
        array[3] = array[3] / sum;

        return array;

    }

    @Override
    public void update() {

    }
}
