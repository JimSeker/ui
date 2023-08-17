package edu.cs4730.viewpagerdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.viewpagerdemo.databinding.RightBinding;

/**
 * This is a simple fragment to display data and it the "right" most fragment in the viewpager.
 *   The code here is identical to the code in the left fragment.
 */

public class FragRight extends Fragment {
    RightBinding binding;
    DataViewModel mViewModel;
    String TAG = "Right";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
        if (savedInstanceState != null) {
            Log.d(TAG, "OnCreate savedInstanceState");
        }
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Left", "OnCreateView");
        binding = RightBinding.inflate(inflater, container, false);

        mViewModel.getDataRight().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                binding.tvright.setText(data);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "OnSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}
