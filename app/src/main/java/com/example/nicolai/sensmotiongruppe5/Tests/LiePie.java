package com.example.nicolai.sensmotiongruppe5.Tests;

import com.example.nicolai.sensmotiongruppe5.Interface.IChart;

public class LiePie implements IChart {


    @Override
    public int[] getData() {
        int[] s = {10, 10, 10, 40, 10, 10, 10};
        return s;
    }

    @Override
    public void update() {

    }
}
