package com.example.ren.day03zuo2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private View view;
    private ImageView mImg;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mImg = (ImageView) view.findViewById(R.id.img);

        Bundle arguments = getArguments();
        String img = (String) arguments.get("img");
        int i = (int) arguments.get("i");

        Glide.with(getActivity()).load(img).into(mImg);
    }
}
