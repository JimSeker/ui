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

import edu.cs4730.fragcomnavlivedemo.databinding.FragmentMainBinding;

/**
 */
public class MainFragment extends Fragment {
    FragmentMainBinding binding;
    DataViewModel mViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        //button to call firstfragment
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the navigation to call the action to change to first fragment.
                //can change to the first fragment and send a simple string as well.
                mViewModel.incr_One();
                mViewModel.setItem("Called From MainFrag");
                Navigation.findNavController(v).navigate(R.id.action_main_to_first);
            }
        });
        //button to call secondfragment.
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the navigation to call the action to change to second fragment.
                mViewModel.incr_Two();
                mViewModel.setItem("Called From MainFrag");
                Navigation.findNavController(v).navigate(R.id.action_main_to_second);
            }
        });

        mViewModel.getoneLD().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
                binding.tv1.setText("Parameter1: " + v);
            }
        });
        mViewModel.gettwoLD().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
                binding.tv2.setText("Parameter1: " + v);
            }
        });


        return binding.getRoot();
    }

}