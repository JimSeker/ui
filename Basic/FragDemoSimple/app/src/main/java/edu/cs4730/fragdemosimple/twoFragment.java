package edu.cs4730.fragdemosimple;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.cs4730.fragdemosimple.databinding.TwoFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class twoFragment extends Fragment {

    private TwoFragmentBinding binding;

    public twoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = TwoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
