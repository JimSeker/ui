package edu.cs4730.modelviewrecyclerviewdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * A simple fragment that displays a recyclerview
 *
 * It does setup the ViewModel observer to see when the data changes, and logs it as part of
 * the test to how it could work.
 *
 */
public class MainFragment extends Fragment {

    DataViewModel mViewModel;
    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    String TAG = "MainFragment";
    private List<String> mList;

    public MainFragment() {
        mList = Arrays.asList("Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = myView.findViewById(R.id.listtrans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(mList, R.layout.row_layout, this);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        //needed the activity, Doc's Creates a ViewModelProvider, which retains ViewModels while a scope of given Activity is alive.
        //otherwise, activity is not the same instance and so not triggered either.
        mViewModel = ViewModelProviders.of(getActivity()).get( DataViewModel.class);
        mViewModel.getItemLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, "triggered "  + s);
            }
        });

        return myView;
    }

}
