package com.example.nicolai.sensmotiongruppe5.Tests;

import com.example.nicolai.sensmotiongruppe5.Interface.IPieChart;

public class LiePie implements IPieChart {


    @Override
    public int[] getData() {
        int[] s = {20, 20, 20, 40};
        return s;
    }
}
