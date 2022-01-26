package edu.cs4730.ListDemo;

/*
 * much of this code was adapted from
 * http://www.vogella.de/articles/AndroidListView/article.html
 *
 */

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

public class Simple1_ListFragment extends ListFragment {

    String TAG = "Simple_frag";
    String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listfragment_layout, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        return view;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String)  values[position];   //getListAdapter().getItem(position);
        Toast.makeText(requireContext(), item + " selected", Toast.LENGTH_LONG).show();
    }

}
