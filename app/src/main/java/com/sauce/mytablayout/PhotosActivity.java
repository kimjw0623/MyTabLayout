package com.sauce.mytablayout;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by deepshikha on 20/3/17.
 */

public class PhotosActivity extends AppCompatActivity {
    int int_position;
    private GridView gridView;
    GridViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        gridView = (GridView)findViewById(R.id.gv_folder);
        int_position = getIntent().getIntExtra("value", 0);
        adapter = new GridViewAdapter(this,ShowImage.al_images,int_position);
        gridView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                       try {
                                           PackageManager pm = getPackageManager();

                                           final ResolveInfo mInfo = pm.resolveActivity(i, 0);

                                           Intent intent = new Intent();
                                           intent.setComponent(new ComponentName(mInfo.activityInfo.packageName, mInfo.activityInfo.name));
                                           intent.setAction(Intent.ACTION_MAIN);
                                           intent.addCategory(Intent.CATEGORY_LAUNCHER);

                                           startActivity(intent);
                                       } catch (Exception e) {
                                           Log.i("TAG", "Unable to launch camera: " + e);
                                       }
                                   }
                               }
        );

    }
}
