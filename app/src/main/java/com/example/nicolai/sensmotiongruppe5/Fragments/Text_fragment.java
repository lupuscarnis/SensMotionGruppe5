package com.example.nicolai.sensmotiongruppe5.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.R;

public class Text_fragment extends android.support.v4.app.Fragment {

    private IChild_OnFragmentInteractionListener mListener;
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    View view;

    public static Text_fragment newInstance(String message) {
        Text_fragment fragment = new Text_fragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }
    public Text_fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView s = view.findViewById(R.id.textbox);
        s.setText(getArguments().getString(EXTRA_MESSAGE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            return view = inflater.inflate(R.layout.text_fragment_layout, container, false);
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IChild_OnFragmentInteractionListener) {
            mListener = (IChild_OnFragmentInteractionListener) context;
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

}
