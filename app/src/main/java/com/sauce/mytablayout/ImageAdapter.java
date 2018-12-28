package com.sauce.mytablayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.asd, R.drawable.asdf,
            R.drawable.a1, R.drawable.b1,R.drawable.c1,
            R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,
            R.drawable.a8,R.drawable.a7,R.drawable.b1,R.drawable.b1,
            R.drawable.b1,R.drawable.b1,R.drawable.b1,R.drawable.b1,
            R.drawable.c1,
            R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,
            R.drawable.a8,R.drawable.a7,R.drawable.b1
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
        return imageView;
    }

}
