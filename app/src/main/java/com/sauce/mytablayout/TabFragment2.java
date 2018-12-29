package com.sauce.mytablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class TabFragment2 extends Fragment {
    private int[] mThumbIds = {
            R.drawable.asd, R.drawable.asdf,
            R.drawable.a1, R.drawable.b1, R.drawable.c1,
            R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
            R.drawable.a8, R.drawable.a7, R.drawable.b1, R.drawable.b1,
            R.drawable.b1, R.drawable.b1, R.drawable.b1, R.drawable.b1,
            R.drawable.c1,
            R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
            R.drawable.a8, R.drawable.a7, R.drawable.b1
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        View view = inflater.inflate(R.layout.tab_fragment_2,null);
        //Intent intent1 = new Intent(getActivity(),New2.class);
        //startActivity(intent1);
        GridView gridView = (GridView) view.findViewById(R.id.grid_view);
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(),mThumbIds);
        // Instance of ImageAdapter Class
        gridView.setAdapter(imageAdapter);
        return view;
    }
}
