package edu.cs4730.bottomnavigationviewdemo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cs4730.bottomnavigationviewdemo.databinding.FragmentTwoBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    FragmentTwoBinding binding;
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
