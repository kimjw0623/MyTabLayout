package com.sauce.mytablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


public class TabFragment2 extends Fragment {
    //Button button;
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
        final View view = inflater.inflate(R.layout.tab_fragment_2,null);
        //Intent intent1 = new Intent(getActivity(),New2.class);
        //startActivity(intent1);
        final GridView gridView = (GridView) view.findViewById(R.id.grid_view);
        final ImageAdapter imageAdapter = new ImageAdapter(view.getContext(),mThumbIds);
        // Instance of ImageAdapter Class


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ShowImage.class);
                //intent.putExtra("name", titleStr);
                //intent.putExtra("phone", descStr);
                startActivity(intent);
            }
        });

        gridView.setAdapter(imageAdapter);
        return view;

    }
}