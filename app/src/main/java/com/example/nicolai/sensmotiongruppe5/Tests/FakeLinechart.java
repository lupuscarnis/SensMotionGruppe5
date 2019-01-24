package com.example.nicolai.sensmotiongruppe5.Tests;

import com.example.nicolai.sensmotiongruppe5.Interface.IChart;

import java.util.ArrayList;

public class FakeLinechart implements IChart {

    private int sum = 0;


    public ArrayList<int[]> getLineValues() {
        ArrayList<int[]> s = new ArrayList<>();
        s.add(new int[]{10, 20, 40, 50, 60, 0});
        s.add(new int[]{40, 30, 70, 10, 40, 0});
        s.add(new int[]{20, 10, 80, 60, 10, 0});
        s.add(new int[]{40, 24, 41, 56, 62, 0});
        s.add(new int[]{40, 20, 20, 10, 44, 0});
        s.add(new int[]{30, 12, 32, 52, 31, 0});
        s.add(new int[]{67, 65, 52, 31, 32, 0});


        return s;

    }

    public int[] getValueArray() {
        int[] s = new int[10];


        for (int i = 1; i <= 10; i++) {
            s[i - 1] = 1440 / i;
        }

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
