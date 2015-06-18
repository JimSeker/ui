package edu.cs4730.recyclerviewdemo3;

/*
 *  A "simple" version of the recyclerview with layout.
   *  There is no simple layout or adapter, so both have to be created.
   *  Everything is labeled, simple1_ that goes with this one.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Simple1_Fragment extends Fragment {

    String TAG = "Simple_rv";
    Context myContext;
    RecyclerView mRecyclerView;
    Simple1_myAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.simple1_fragment, container, false);
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};
        //setup the RecyclerView
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(myContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Simple1_myAdapter(values, R.layout.simple1_rowlayout, myContext);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);
        return myView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myContext = activity.getApplicationContext();
        Log.d(TAG, "onAttach");
    }


}
