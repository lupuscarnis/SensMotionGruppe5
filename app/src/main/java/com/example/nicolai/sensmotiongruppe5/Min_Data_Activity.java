package com.example.nicolai.sensmotiongruppe5;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.BLL.DAOHandler;
import com.example.nicolai.sensmotiongruppe5.Fragments.Line_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Fragments.Pie_chart_frag;
import com.example.nicolai.sensmotiongruppe5.Interface.IData;
import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.jaygoo.widget.SeekBar;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;


public class Min_Data_Activity extends Fragment {

    MyPageAdapter pageAdapter;
    RangeSeekBar bar;
    private String[] strigns;
    private IParent_OnFragmentInteractionListener mListener;
    private View rootView;
    private ViewPager pager;
    private TextView textView1, textView2, textView3,textView4, textView5,textView6, textView7;
    private Button button1, button2, button3, button4, button5, button6, button7;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup drawer_layout,
                             Bundle savedInstanceState) {










        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_min__data, drawer_layout, false);


        }



        //View pager dots
        SpringDotsIndicator springDotsIndicator = rootView.findViewById(R.id.spring_dots_indicator);
        final ViewPager viewPager = rootView.findViewById(R.id.min_data_fragment_pager);
        DotIndicatorPagerAdapter adapter = new DotIndicatorPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setClipToPadding(false);
        viewPager.setPadding(30, 0, 30, 0);
        springDotsIndicator.setViewPager(viewPager);

        button1 = rootView.findViewById( R.id.button1 );
        button2 = rootView.findViewById( R.id.button2 );
        button3 = rootView.findViewById( R.id.button3 );
        button4 = rootView.findViewById( R.id.button4 );
        button5 = rootView.findViewById( R.id.button5 );
        button6 = rootView.findViewById( R.id.button6 );
        button7 = rootView.findViewById( R.id.button7 );

        textView1 = rootView.findViewById( R.id.textView1 );
        textView2 =rootView.findViewById( R.id.textView2 );
        textView3 =rootView.findViewById( R.id.textView3 );
        textView4 =rootView.findViewById( R.id.textView4);
        textView5=rootView.findViewById( R.id.textView5 );
        textView6 =rootView.findViewById( R.id.textView6 );
        textView7 =rootView.findViewById( R.id.textView7 );

        textView1.setText( "I ro");
        textView2.setText( "Stående");
        textView3.setText( "Gående");
        textView4.setText( "Exercise");
        textView5.setText( "Cyklende");
        textView6.setText( "Other");
        textView7.setText( "Ingen Data");


        button1.setBackgroundColor(Color.BLUE);
        button2.setBackgroundColor(Color.GRAY);
        button3.setBackgroundColor(Color.RED);
        button4.setBackgroundColor(Color.BLACK);
        button5.setBackgroundColor(Color.parseColor( "#006400" ));//GREEN
        button6.setBackgroundColor(Color.parseColor( "#FFD700")); //GOLD
        button7.setBackgroundColor(Color.parseColor( "#9932CC" ));//PURPLE

        textView1.setTextColor(Color.BLACK);
        textView2.setTextColor(Color.BLACK);
        textView3.setTextColor(Color.BLACK);
        textView4.setTextColor(Color.BLACK);
        textView5.setTextColor(Color.BLACK);
        textView6.setTextColor(Color.BLACK);
        textView7.setTextColor(Color.BLACK);







        // Range Seekerbar start



        bar = rootView.findViewById(R.id.Min_data_range_silder);
        SeekBar leftSeekBar = bar.getLeftSeekBar();
        SeekBar rightSeekBar = bar.getRightSeekBar();
        leftSeekBar.setThumbDrawableId(R.drawable.slider_dot);
        leftSeekBar.setThumbSize(10);
        rightSeekBar.setThumbSize(10);
        rightSeekBar.setThumbDrawableId(R.drawable.slider_dot);
        bar.setTickMarkMode(RangeSeekBar.TRICK_MARK_MODE_OTHER);

        bar.setTickMarkTextColor(Color.parseColor("#03A9F4"));
        bar.setRange(0, 6, 1);
        bar.setValue(0, 6);
        bar.setTickMarkTextArray(new String[]{"Getting Data", "Getting Data", "Getting Data", "Getting Data"});
        IData s = new DAOHandler();
        new GetData(new GetData.AsyncResponse() {
            @Override
            public void processFinish(String[] output) {
                strigns = output;
                bar.setTickMarkTextArray(formatString(output));
                bar.invalidate();
            }
        }).execute(s);


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
                new DAOHandler().setDAOCurrentDates(strigns[Math.round(left)], strigns[Math.round(right)]);
                viewPager.getAdapter().notifyDataSetChanged();

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
        // fList.add(Bar_graph_frag.newInstance("Fragment 3"));


        return fList;

    }

    private String[] formatString(String[] Array) {
        String[] s = new String[7];
        for (int i = 0; i < Array.length; i++) {

            s[i] = Array[i].substring(0, 5);

        }


        return s;
    }


}

class GetData extends AsyncTask<IData, Void, String[]> {
    public AsyncResponse delegate = null;
    private String[] str;

    public GetData(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String[] doInBackground(IData... iData) {

        str = iData[0].getAllDates();


        return str;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
        delegate.processFinish(strings);

    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }


    public interface AsyncResponse {
        void processFinish(String[] output);
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

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}


