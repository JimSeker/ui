package edu.cs4730.archnavigationdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cs4730.archnavigationdemo.databinding.FragmentThreeBinding;

/**
 * This example receives arguments via the safe_arg version.  Note safe args are included in Project build.grade (not module).
 */
public class Fragment_Three extends Fragment {

    FragmentThreeBinding binding;

    public Fragment_Three() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false);
        binding.tvPassed.setText(Fragment_ThreeArgs.fromBundle(requireArguments()).getMessage());

        String stuff = "Data 2 is " + Fragment_ThreeArgs.fromBundle(requireArguments()).getNumber();
        binding.tvPassed2.setText(stuff);
        return binding.getRoot();
    }

}

