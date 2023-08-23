package edu.cs4730.fragcomnavvmodeldemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.cs4730.fragcomnavvmodeldemo.databinding.FragmentSecondBinding;

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
public class SecondFragment extends Fragment {
    FragmentSecondBinding binding;
    DataViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the interface, which call into the activity, so it
                mViewModel.num_one++;
                mViewModel.setItem("Called by SecondFragment");
                Navigation.findNavController(v).navigate(R.id.action_second_to_first);
            }
        });
        binding.tv1.setText("Parameter1: " + mViewModel.num_two);

        mViewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                binding.tv2.setText("Parameter2: " + data);
            }
        });

        return binding.getRoot();
    }

}
