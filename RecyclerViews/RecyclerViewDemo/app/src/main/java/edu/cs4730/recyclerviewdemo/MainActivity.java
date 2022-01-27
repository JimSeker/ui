package edu.cs4730.recyclerviewdemo;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A very simple example of how to setup a RecyclerView with cards views.
 * A  RecyclerView.Adapter needs to be implemented inorder for it to work.
 * There is not a "simple" adapter, it must be extended.  see myAdapter.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    myAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> values = Arrays.asList("Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2");

        //setup the RecyclerView
        mRecyclerView =  findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //and default animator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new myAdapter(values, R.layout.my_row, this);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);
    }
}
