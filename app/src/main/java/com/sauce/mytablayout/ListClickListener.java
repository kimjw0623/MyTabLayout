package com.sauce.mytablayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ListClickListener implements OnClickListener {

    Context context;
    String name;
    String phone;

    public ListClickListener(Context context, String name,String phone) {
        this.context = context;
        this.name = name;
        this.phone = phone;
    }
    public void onClick(View v){
        Intent intent = new Intent(context, ListViewActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }
}
