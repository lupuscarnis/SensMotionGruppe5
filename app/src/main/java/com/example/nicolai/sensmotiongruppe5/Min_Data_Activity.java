package com.example.nicolai.sensmotiongruppe5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.nicolai.sensmotiongruppe5.Fragments.Line_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Pie_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;
import com.example.nicolai.sensmotiongruppe5.Tests.testForSlider;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;


public class Min_Data_Activity extends FragmentActivity {

    MyPageAdapter pageAdapter;
    RangeSeekBar bar;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        IData s = new testForSlider();

        setContentView(R.layout.activity_min__data);
        List<Fragment> fragments = getFragments();

    // Range Seekerbar start



        bar = findViewById(R.id.Min_data_range_silder);
        SeekBar leftSeekBar = bar.getLeftSeekBar();
        SeekBar rightSeekBar = bar.getRightSeekBar();
        leftSeekBar.setThumbDrawableId(R.drawable.blackline);
        rightSeekBar.setThumbDrawableId(R.drawable.blackline);
        bar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_OTHER);
        bar.setTickMarkTextArray(s.getAllDates());
        bar.setTickMarkTextColor(Color.parseColor("#03A9F4"));
        bar.setRange(0, 6, 1);
        bar.setOnRangeChangedListener(new OnRangeChangedListener() {
            float left,right;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                left = leftValue;
                right = rightValue;


            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {


            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

                bar.setValue(Math.round(left), Math.round(right));
            }
        });
    // Range Seekerbar end



        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager = findViewById(R.id.min_data_fragment_pager);
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

