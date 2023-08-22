package edu.cs4730.recyclerviewdemo3;

/**
 * A "simple" version of the recyclerview with layout.
 * There is no simple layout or adapter, so both have to be created.
 * Everything is labeled, simple1_ that goes with this one.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo3.databinding.Simple1FragmentBinding;

public class Simple1_Fragment extends Fragment {

    Simple1FragmentBinding binding;
    Simple1_myAdapter mAdapter;
    String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = Simple1FragmentBinding.inflate(inflater, container, false);

        //setup the RecyclerView
        binding.list.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Simple1_myAdapter(values, R.layout.simple1_rowlayout, requireContext());
        //add the adapter to the recyclerview
        binding.list.setAdapter(mAdapter);
        return binding.getRoot();
    }

}
