package com.example.ren.day03zuo3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {


    private View view;
    private TextView mTabTv;

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTabTv = (TextView) view.findViewById(R.id.tab_tv);
    }
}
