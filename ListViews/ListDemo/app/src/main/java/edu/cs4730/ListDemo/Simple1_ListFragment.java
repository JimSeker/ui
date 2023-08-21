package edu.cs4730.ListDemo;

/*
 * much of this code was adapted from
 * http://www.vogella.de/articles/AndroidListView/article.html
 *
 */


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import edu.cs4730.ListDemo.databinding.ListfragmentLayoutBinding;

public class Simple1_ListFragment extends ListFragment {

    String TAG = "Simple_frag";
    String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2", "VMS", "DOS", "Other", "Chrome"};
    ListfragmentLayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = ListfragmentLayoutBinding.inflate(inflater, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        return binding.getRoot();

    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        String item = values[position];   //getListAdapter().getItem(position);
        Toast.makeText(requireContext(), item + " selected", Toast.LENGTH_LONG).show();
    }

}
