package com.sauce.mytablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;


public class TabFragment3 extends Fragment {

    private MyView mview;
    private LinearLayout ll;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);
        MyView mview = new MyView(getActivity());
        //mview = (MyView)inflater.inflate(R.layout.tab_fragment_3, container, false);
        ll = (LinearLayout)view.findViewById(R.id.linearlayout);
        ll.addView(mview);

        return view;
    }
}
