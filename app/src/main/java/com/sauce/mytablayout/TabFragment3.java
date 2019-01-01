package com.sauce.mytablayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Junyoung on 2016-06-23.
 */

public class TabFragment3 extends Fragment {

    private MyView mview;
    private LinearLayout ll;
    private Context first;
    private int cnt=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    2);  //마지막 인자는 체크해야될 권한 갯수

        }


        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);
        mview = new MyView(first = getActivity());
        ll = (LinearLayout)view.findViewById(R.id.linearlayout);
        ll.addView(mview);

        Button button1 = (Button)view.findViewById(R.id.button1);
        Button button2 = (Button)view.findViewById(R.id.button2);
        Button button3 = (Button)view.findViewById(R.id.button3);
        Button button4 = (Button)view.findViewById(R.id.button4);
        Button button5 = (Button)view.findViewById(R.id.button5);
        Button cleaner = (Button)view.findViewById(R.id.cleaner);
        Button saver = (Button)view.findViewById(R.id.saver);
        Button eraser = (Button)view.findViewById(R.id.eraser);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.changeColor("#ffd4e5");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.changeColor("#d4ffea");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.changeColor("#eecbff");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.changeColor("#feffa3");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.changeColor("#dbdcff");
            }
        });

        cleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.cleanPath();
            }
        });
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.buildDrawingCache();
                Bitmap bitmap = ll.getDrawingCache();

                String file_name = "capture"+(cnt++)+".jpg";
                FileOutputStream fout = null;
                try{
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                            , file_name);
                    if(file.exists()) file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "capture"+(cnt++)+".jpg");
                    fout = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);

                    Intent intent= new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(file));
                    getActivity().sendBroadcast(intent);


                    fout.flush();
                    fout.close();

                    Toast.makeText(getActivity(), "Your Drawing Saved.", Toast.LENGTH_SHORT).show();

                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e2){
                    e2.printStackTrace();
                }
            }
        });
        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.changeColor("#ffffff");
            }
        });

        return view;
    }
}