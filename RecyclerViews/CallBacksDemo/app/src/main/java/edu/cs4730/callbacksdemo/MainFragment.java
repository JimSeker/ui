package edu.cs4730.callbacksdemo;

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

import edu.cs4730.callbacksdemo.databinding.FragmentMainBinding;

/**
 * A simple fragment that displays a recyclerview and has a callback
 * plus creates the listener needed in the adapter.
 */
public class MainFragment extends Fragment {

    private OntransInteractionCallback mCallback;
    FragmentMainBinding binding;
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
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.myRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(mList, R.layout.row_layout, requireActivity());
        mAdapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String id) {
                // String name = users.get(position).name;
                Log.v(TAG, "Listener at " + TAG);
                Toast.makeText(requireContext(), "MainFragment: " + id + " was clicked!", Toast.LENGTH_SHORT).show();
                mCallback.ontransInteraction(id);
            }
        });
        //add the adapter to the recyclerview
        binding.myRecyclerView.setAdapter(mAdapter);

        return binding.getRoot();
    }

    //use this one instead of the one above.  Note it's the parameter, context instead of activity.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (OntransInteractionCallback) getActivity();
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
