package com.sauce.mytablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Junyoung on 2016-06-23.
 */

public class TabFragment1 extends Fragment {
    //static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab_fragment_1, container, false);
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        ListViewAdapter adapter = new ListViewAdapter();
                //ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;

        ListView listview = (ListView) view.findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        adapter.addItem("John", "010-1234-5678") ;
        adapter.addItem("Sam", "010-1111-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;
        adapter.addItem("Pat", "010-1222-5678") ;

        return view;
    }
}
