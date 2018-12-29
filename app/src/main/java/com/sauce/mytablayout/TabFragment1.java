package com.sauce.mytablayout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;
import org.json.*;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import android.database.Cursor;


public class TabFragment1 extends Fragment {
    //static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"} ;

    String [] arrProjection = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab_fragment_1, container, false);
        final View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        ListViewAdapter adapter = new ListViewAdapter(view.getContext());
                //ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;
        ListView listview = (ListView) view.findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;
/*
        Cursor clsCursor = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
                null,null
        );

        while (clsCursor.moveToNext()) {
                String name = clsCursor.getString(clsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = clsCursor.getString(clsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                adapter.addItem(name,phone);
        }
        clsCursor.close();
*/

        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < obj.length(); i++) {
                JSONObject jo_inside = obj.getJSONObject(i);
                String name_value = jo_inside.getString("name");
                String phone_value = jo_inside.getString("phone");
                adapter.addItem(name_value, phone_value) ;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;

                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("name", titleStr);
                intent.putExtra("phone", descStr);
                startActivity(intent);
            }
        }) ;

        return view;
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("generated.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
