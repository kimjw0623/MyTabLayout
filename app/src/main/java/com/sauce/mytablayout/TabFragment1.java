package com.sauce.mytablayout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import android.database.Cursor;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class TabFragment1 extends Fragment {
    private RequestQueue queue;
    private static final String TAG = "MAIN";
    //static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"} ;
    ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;
    String [] arrProjection = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };
    String name = null;
    String num= null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab_fragment_1, container, false);
        final View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        final ListViewAdapter adapter = new ListViewAdapter(view.getContext(),itemList);
        //ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;
        final ListView listview = (ListView) view.findViewById(R.id.listview1) ;
        final ArrayList<String> items = new ArrayList<String>() ;
/*
        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < obj.length(); i++) {
                JSONObject jo_inside = obj.getJSONObject(i);
                String name_value = jo_inside.getString("name");
                String phone_value = jo_inside.getString("phone");
                adapter.addItem(name_value, phone_value);
                items.add(Integer.toString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        queue = Volley.newRequestQueue(getContext());
        String url = "http://socrip4.kaist.ac.kr:580/contact/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray obj = new JSONArray(response);
                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject jo_inside = obj.getJSONObject(i);
                        String name_value = jo_inside.getString("name");
                        String phone_value = jo_inside.getString("phone");
                        adapter.addItem(name_value, phone_value);
                        items.add(Integer.toString(i));
                    }
                } catch (JSONException e) {
                e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        stringRequest.setTag(TAG);
        queue.add(stringRequest);





        EditText editTextFilter = (EditText) view.findViewById(R.id.editTextFilter) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                if(filterText.length()==0)
                    listview.clearTextFilter() ;
                ((ListViewAdapter)listview.getAdapter()).getFilter().filter(filterText) ;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        }) ;

        Button buttonNoAsc = (Button) view.findViewById(R.id.namedesc) ;
        buttonNoAsc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(getContext(), ListViewAdd.class);
                startActivityForResult(intent,0);
                adapter.addItem(name,num);
                adapter.notifyDataSetChanged() ;*/
                Comparator<ListViewItem> noAsc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        //return (Integer.parseInt(item1.getTitle()) - Integer.parseInt(item2.getTitle())) ;
                        return item2.getTitle().compareTo(item1.getTitle()) ;
                    }
                } ;
                Collections.sort(itemList, noAsc) ;
                adapter.notifyDataSetChanged() ;
            }
        });

        Button addButton = (Button)view.findViewById(R.id.nameasc) ;
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                /*
                int count;
                count = adapter.getCount();

                // 아이템 추가.
                items.add("LIST" + Integer.toString(count + 1));
                adapter.addItem("tom", "01012345678") ;

                // listview 갱신
                adapter.notifyDataSetChanged();*////두번째 액티비티에서 데이터 전달하도록
                /*
                Intent intent = new Intent(getActivity(), ListViewAdd.class);
                startActivityForResult(intent,0);
                adapter.addItem(name,num);
                adapter.notifyDataSetChanged() ;*/
                Comparator<ListViewItem> noAsc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        //return (Integer.parseInt(item1.getTitle()) - Integer.parseInt(item2.getTitle())) ;
                        return item1.getTitle().compareTo(item2.getTitle()) ;
                    }
                } ;
                Collections.sort(itemList, noAsc) ;
                adapter.notifyDataSetChanged() ;
            }
        }) ;


        Button deleteButton = (Button)view.findViewById(R.id.numasc) ;
        deleteButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItem> noAsc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        //return (Integer.parseInt(item1.getTitle()) - Integer.parseInt(item2.getTitle())) ;
                        return item1.getDesc().compareTo(item2.getDesc()) ;
                    }
                } ;
                Collections.sort(itemList, noAsc) ;
                adapter.notifyDataSetChanged() ;
            }
        });
        Button numdesc = (Button)view.findViewById(R.id.numdesc) ;
        numdesc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItem> noAsc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        //return (Integer.parseInt(item1.getTitle()) - Integer.parseInt(item2.getTitle())) ;
                        return item2.getDesc().compareTo(item1.getDesc()) ;
                    }
                } ;
                Collections.sort(itemList, noAsc) ;
                adapter.notifyDataSetChanged() ;
            }
        });
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
        listview.setAdapter(adapter) ;
        adapter.notifyDataSetChanged();

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
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 0:
                //itemList.addItem(data.getStringExtra("name"),data.getStringExtra("num"));
                name = data.getStringExtra("name");
                num = data.getStringExtra("num");
                break;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }
}