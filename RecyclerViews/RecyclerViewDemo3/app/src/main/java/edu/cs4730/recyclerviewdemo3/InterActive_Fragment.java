package edu.cs4730.recyclerviewdemo3;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo3.databinding.InteractiveFragmentBinding;


public class InterActive_Fragment extends Fragment {
    String TAG = "InterActive RV";
    InteractiveFragmentBinding binding;
    InterActive_myAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = InteractiveFragmentBinding.inflate(inflater, container, false);

        //setup the RecyclerView
       binding.list.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new InterActive_myAdapter(getModel(),  requireContext());
        //add the adapter to the recyclerview
        binding.list.setAdapter(mAdapter);

        return binding.getRoot();
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
