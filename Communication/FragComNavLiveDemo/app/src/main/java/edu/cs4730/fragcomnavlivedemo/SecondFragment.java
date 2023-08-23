package edu.cs4730.fragcomnavlivedemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.cs4730.fragcomnavlivedemo.databinding.FragmentSecondBinding;

/**
 *
 */
public class SecondFragment extends Fragment {
    FragmentSecondBinding binding;
    DataViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        mViewModel.gettwoLD().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
                binding.tv1.setText("Parameter1: " +v);
            }
        });
        mViewModel.getItemLD().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.tv2.setText("Parameter1: " +s);
            }
        });

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the interface, which call into the activity, so it
                mViewModel.incr_One();
                mViewModel.setItem("Called by SecondFragment");
                Navigation.findNavController(v).navigate(R.id.action_second_to_first);
            }
        });

        return binding.getRoot();
    }


}
