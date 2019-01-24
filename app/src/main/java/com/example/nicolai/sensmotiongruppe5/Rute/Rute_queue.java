package com.example.nicolai.sensmotiongruppe5.Rute;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.nicolai.sensmotiongruppe5.Fragments.Nested_Fragments.Default_fragment;
import com.example.nicolai.sensmotiongruppe5.R;

import java.util.ArrayList;

public class Rute_queue {
    private static Rute_queue rute_queue = null;
    boolean replace = false;
    ArrayList<Fragment> fraList;
    FragmentManager fragmentManager;

    public Rute_queue(FragmentManager fm) {
        fraList = new ArrayList<>();
        fragmentManager = fm;

    }

    @Nullable
    public static Rute_queue getInstance(@Nullable FragmentManager fm) {
        if (rute_queue == null)
            rute_queue = new Rute_queue(fm);

        return rute_queue;
    }

    public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    @Nullable
    public void replaceFragment(@Nullable Fragment fra, boolean replace) {


        if (fra != null && !(replace) && fraList.size() == 0) {
            fraList.add(fra);

            FragmentTransaction sb = fragmentManager.beginTransaction();
            sb.addToBackStack(null);
            sb.replace(R.id.highlight_frame, fraList.get(fraList.size() - 1));
            sb.commitAllowingStateLoss();


        } else if (fra != null && !(replace)) {
            fraList.add(fra);
        }


        if (replace && fraList.size() > 0) {

            FragmentTransaction sb = fragmentManager.beginTransaction();
            sb.replace(R.id.highlight_frame, fraList.get(fraList.size() - 1));
            sb.addToBackStack(null);
            sb.commitAllowingStateLoss();
            fraList.remove(0);


        } else if (fraList.size() == 0) {
            FragmentTransaction sb = fragmentManager.beginTransaction();
            sb.addToBackStack(null);
            sb.replace(R.id.highlight_frame, Default_fragment.newInstance("1", "2", "3", "31"));
            sb.commitAllowingStateLoss();


        }

    }

}



