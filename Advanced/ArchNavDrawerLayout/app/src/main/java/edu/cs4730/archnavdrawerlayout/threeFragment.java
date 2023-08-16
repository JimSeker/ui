package edu.cs4730.archnavdrawerlayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.cs4730.archnavdrawerlayout.databinding.FragmentThreeBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class threeFragment extends Fragment {

    FragmentThreeBinding binding;

    public threeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
