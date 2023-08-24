package edu.cs4730.supportdesigndemo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.cs4730.supportdesigndemo.databinding.FragmentFabBinding;


/**
 * A simple demo of the Foating Action button.
 * This code does very little and looks just like you would use a button actualy.
 * see the xml doc.
 */
public class FABFragment extends Fragment {
    FragmentFabBinding binding;
    public FABFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFabBinding.inflate(inflater, container, false);
        binding.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "You clicked add!", Toast.LENGTH_LONG).show();
            }
        });
        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "You clicked check!", Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }


}
