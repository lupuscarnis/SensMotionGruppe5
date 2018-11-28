package com.example.nicolai.sensmotiongruppe5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.example.nicolai.sensmotiongruppe5.Fragments.Line_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Pie_chart_frag;
import com.jaygoo.widget.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;


public class Min_Data_Activity extends FragmentActivity {

    MyPageAdapter pageAdapter;
    RangeSeekBar bar;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            String[] haha = {"20:20","10:10","5:5","10:2"};

        setContentView(R.layout.activity_min__data);
        List<Fragment> fragments = getFragments();

        bar = findViewById(R.id.Min_data_range_silder);
        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = findViewById(R.id.min_data_fragment_pager);
        bar.setRange(1,10);
        bar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_OTHER);
        bar.setTickMarkTextArray(haha);
        bar.setTickMarkTextColor(Color.parseColor("#03A9F4"));
        pager.setAdapter(pageAdapter);


    }

    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<>();


        fList.add(Line_chart_frag.newInstance("Fragment 1"));
        fList.add(Pie_chart_frag.newInstance("Fragment 2"));


        return fList;

    }


}

class MyPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;


    public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {

        super(fm);

        this.fragments = fragments;

    }

    @Override

    public Fragment getItem(int position) {

        return this.fragments.get(position);

    }


    @Override

    public int getCount() {

        return this.fragments.size();

    }


}

