package com.sauce.mytablayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text);

        TextView name = (TextView) findViewById(R.id.name) ;
        TextView phone = (TextView) findViewById(R.id.phone) ;

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        phone.setText(intent.getStringExtra("phone"));
    }
}
