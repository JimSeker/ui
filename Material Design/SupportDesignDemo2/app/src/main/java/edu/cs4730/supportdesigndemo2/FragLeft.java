package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.supportdesigndemo2.databinding.LeftBinding;

/**
 * This is a simple fragment to display data and it the "left" most fragment in the viewpager.
 *   The code here is identical to the code in the right fragment.
 */
public class FragLeft extends Fragment {
    LeftBinding binding;
    DataViewModel mViewModel;
    String TAG = "Left";

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Left", "OnCreateView");
        binding = LeftBinding.inflate(inflater, container, false);

        mViewModel.getDataLeft().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                binding.tvleft.setText(data);
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "OnSaveInstanceState");
        super.onSaveInstanceState(outState);
    }
}