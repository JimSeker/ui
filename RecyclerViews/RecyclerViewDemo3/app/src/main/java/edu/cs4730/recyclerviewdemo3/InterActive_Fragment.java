package edu.cs4730.recyclerviewdemo3;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InterActive_Fragment extends Fragment {
    String TAG = "InterActive RV";
    RecyclerView mRecyclerView;
    InterActive_myAdapter mAdapter;
    Context myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.interactive_fragment, container, false);


        //setup the RecyclerView
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(myContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new InterActive_myAdapter(getModel(), R.layout.interactive_rowlayout, myContext);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);


        return myView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myContext = context;
        Log.d(TAG, "onAttach");
    }


    private List<InterActive_DataModel> getModel() {
        List<InterActive_DataModel> list = new ArrayList<InterActive_DataModel>();
        list.add(new InterActive_DataModel("Linux"));
        list.add(new InterActive_DataModel("Windows7"));
        list.add(new InterActive_DataModel("Suse"));
        list.add(new InterActive_DataModel("Eclipse"));
        list.add(new InterActive_DataModel("Ubuntu"));
        list.add(new InterActive_DataModel("Solaris"));
        list.add(new InterActive_DataModel("Android"));
        list.add(new InterActive_DataModel("iPhone"));
        // Initially select one of the items
        list.get(1).setSelected(true);
        return list;
    }

}
