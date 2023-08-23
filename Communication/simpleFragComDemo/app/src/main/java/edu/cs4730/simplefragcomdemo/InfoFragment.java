package edu.cs4730.simplefragcomdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.simplefragcomdemo.databinding.FragmentInfoBinding;

/**
 * a simple fragment to display data.
 */
public class InfoFragment extends Fragment {

    FragmentInfoBinding binding;
    int num = 0;
    boolean attached = false;

    public InfoFragment() {
        // Required empty public constructor
    }

    public void update(int i) {
        num += i;
        if (attached)
            binding.numclick.setText("Number of clicks: " + num);
        else
            Log.v("Info", "label is not available yet.");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        binding.numclick.setText("Number of clicks: " + num);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        attached = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        attached = false;
    }
}
