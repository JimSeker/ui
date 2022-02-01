package edu.cs4730.callbacksitemviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple fragment that displays a recyclerview and has a callback
 * plus creates the listener needed in the adapter.
 */
public class MainFragment extends Fragment {

    private OntransInteractionCallback mCallback;
    private RecyclerView mRecyclerView;
    private myAdapter mAdapter;
    final private String TAG = "MainFragment";
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(mList, R.layout.row_layout, requireActivity());
        mAdapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String id) {
                // String name = users.get(position).name;
                Log.v(TAG, "Listener at " + TAG);
                Toast.makeText(getContext(), "MainFragment: " + id + " was clicked!", Toast.LENGTH_SHORT).show();
                //we could just send the id or in this case get the name as something more useful here.
                String name = mAdapter.myList.get(Integer.parseInt(id));
                mCallback.ontransInteraction(name);
            }
        });
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        return myView;
    }


    //use this one instead of the one above.  Note it's the parameter, context instead of activity.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (OntransInteractionCallback) requireActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(requireActivity().toString()
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
