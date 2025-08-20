package edu.cs4730.navigationbottomorrail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.navigationbottomorrail.databinding.FragmentTwoBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    FragmentTwoBinding binding;
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
