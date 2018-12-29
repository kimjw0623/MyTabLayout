
package com.sauce.mytablayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext=null;
    //private LayoutInflater inflater;
    // Keep all Images in array
    int[] imageIDs = null;

    // Constructor
    public ImageAdapter(Context c,int[] imageIDs) {
        this.mContext = c;
        this.imageIDs = imageIDs;
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return imageIDs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;

        if (null != convertView)
            imageView = (ImageView) convertView;
        else {
            Bitmap bmp
                    = BitmapFactory.decodeResource(mContext.getResources(), imageIDs[position]);
            bmp = Bitmap.createScaledBitmap(bmp, 320, 320, false);

            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);

            imageView.setImageBitmap(bmp);

            ImageClickListener imageViewClickListener
                    = new ImageClickListener(mContext, imageIDs[position]);
            imageView.setOnClickListener(imageViewClickListener);
        }
        return imageView;
    }
}