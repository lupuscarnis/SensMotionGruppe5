package com.example.nicolai.sensmotiongruppe5.BLL;

import com.example.nicolai.sensmotiongruppe5.Interface.IChart;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;

import java.util.ArrayList;

public class PieChart implements IChart {


    @Override
    public int[] getData() {

        int[] array = new int[7];
        int[] arrays = new int[7];
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

        arrays[0] = (100 * (array[0] / sum));
        arrays[1] = 100 * (array[1] / sum);
        arrays[2] = 100 * (array[2] / sum);
        arrays[3] = 100 * (array[3] / sum);
        arrays[4] = 100 * (array[4] / sum);
        arrays[5] = 100 * (array[5] / sum);
        arrays[6] = 100 * (array[6] / sum);

        return arrays;

    }

    @Override
    public void update() {

    }
}
