package edu.cs4730.bottomnavigationviewdemo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cs4730.bottomnavigationviewdemo.databinding.FragmentThreeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class threeFragment extends Fragment {
    FragmentThreeBinding binding;

    public threeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentThreeBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

}
