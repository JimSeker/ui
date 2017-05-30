package edu.cs4730.callbacksdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * A simple fragment that displays a recyclerview and has a callback
 * plus creates the listener needed in the adapter.
 */
public class MainFragment extends Fragment {

    private OntransInteractionCallback mCallback;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.listtrans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(mList, R.layout.row_layout, getActivity());
        mAdapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String id) {
                // String name = users.get(position).name;
                Log.v(TAG, "Listener at " + TAG);
                Toast.makeText(getContext(), "MainFragment: " + id + " was clicked!", Toast.LENGTH_SHORT).show();
                mCallback.ontransInteraction(id);
            }
        });
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        return myView;
    }


    //deprecated in support library 23.0.0+  likely not call anymore.
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OntransInteractionCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    //use this one instead of the one above.  Note it's the parameter, context instead of activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OntransInteractionCallback) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    //The interface for the call back code that needs to be implemented.
    public interface OntransInteractionCallback {
        public void ontransInteraction(String item);
    }


}
