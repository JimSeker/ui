package edu.cs4730.fragdemosimple;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.fragdemosimple.databinding.OneFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class oneFragment extends Fragment {

    private OneFragmentBinding binding;
    public oneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = OneFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
