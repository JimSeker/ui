package edu.cs4730.recyclerviewdemo3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A third "simple" example of a recycler view.   Everything with this example is prefix simple3_
 * this uses a cardview as the layout, so it looks "better" and you see where each view is separated.
 */
public class Simple3_Fragment extends Fragment {
    RecyclerView mRecyclerView;
    Simple3_myAdapter mAdapter;
    List<String> values = Arrays.asList("Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2");


    public Simple3_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.simple3_fragment, container, false);
        //setup the RecyclerView
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Simple3_myAdapter(values, R.layout.simple3_rowlayout, requireContext());
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        return myView;
    }

}
