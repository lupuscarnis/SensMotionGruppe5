package com.example.nicolai.sensmotiongruppe5;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;
import com.example.nicolai.sensmotiongruppe5.Fragments.Bar_graph_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Line_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Pie_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;
import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;


public class Min_Data_Activity extends Fragment {

    MyPageAdapter pageAdapter;
    RangeSeekBar bar;
    private IParent_OnFragmentInteractionListener mListener;
    private View rootView;
    private ViewPager pager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_min__data, drawer_layout, false);
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        IData s = new DAOHandler();



        // Range Seekerbar start


        bar = rootView.findViewById(R.id.Min_data_range_silder);
        SeekBar leftSeekBar = bar.getLeftSeekBar();
        SeekBar rightSeekBar = bar.getRightSeekBar();
        leftSeekBar.setThumbDrawableId(R.drawable.blackline);
        rightSeekBar.setThumbDrawableId(R.drawable.blackline);
        bar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_OTHER);


        final String[] Str;
        // s.getALLDate() TODO: set that method in a asyncTask
        Str = s.getAllDates();


        bar.setTickMarkTextArray(formatString(Str));


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
                DAOHandler s = new DAOHandler();
                s.setCurrentDate(Str[Math.round(left)], Str[Math.round(right)]);
            }
        });
        // Range Seekerbar end



        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        List<Fragment> fragments = getFragments();
        pageAdapter = new MyPageAdapter(getChildFragmentManager(), fragments);
        pager = rootView.findViewById(R.id.min_data_fragment_pager);
        pager.setAdapter(pageAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IParent_OnFragmentInteractionListener) {
            mListener = (IParent_OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void messageFromParentFragment(Uri uri);
    }


    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<>();


        fList.add(Line_chart_frag.newInstance("Fragment 1"));
        fList.add(Pie_chart_frag.newInstance("Fragment 2"));
        fList.add(Bar_graph_frag.newInstance("Fragment 3"));


        return fList;

    }

    private String[] formatString(String[] Array) {
        String[] s = new String[7];
        for (int i = 0; i < Array.length; i++) {

            s[i] = Array[i].substring(6);

        }


        return s;
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

