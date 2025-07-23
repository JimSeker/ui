package edu.cs4730.recyclerviewdemo3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo3.databinding.Simple3FragmentBinding;


/**
 * A third "simple" example of a recycler view.   Everything with this example is prefix simple3_
 * this uses a cardview as the layout, so it looks "better" and you see where each view is separated.
 */
public class Simple3_Fragment extends Fragment {
    Simple3FragmentBinding binding;
    Simple3_myAdapter mAdapter;
    List<String> values = Arrays.asList("Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2");


    public Simple3_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = Simple3FragmentBinding.inflate(inflater, container, false);
        //setup the RecyclerView
        binding.list.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Simple3_myAdapter(values, requireContext());
        //add the adapter to the recyclerview
        binding.list.setAdapter(mAdapter);

        return binding.getRoot();
    }

}
