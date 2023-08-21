package edu.cs4730.archnavigationdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cs4730.archnavigationdemo.databinding.FragmentBundleBinding;

/**
 * This is a simple fragment to show use to receive a bundle via the nav_graph.  see fragment_two for the
 * bundle creation and send.
 */
public class Fragment_bundle extends Fragment {

    FragmentBundleBinding binding;

    public Fragment_bundle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBundleBinding.inflate(inflater, container, false);

        //via a bundle, instead of safe args
        binding.btvPassed.setText(requireArguments().getString("message", "no data"));
        String stuff = "Data 2 is " + requireArguments().getInt("number", 1);
        binding.btvPassed2.setText(stuff);
        return binding.getRoot();
    }

}
