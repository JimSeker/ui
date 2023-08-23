package edu.cs4730.fragcomnavlivedemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.cs4730.fragcomnavlivedemo.databinding.FragmentFirstBinding;

/**
 *
 */
public class FirstFragment extends Fragment {
    FragmentFirstBinding binding;
    DataViewModel mViewModel;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        mViewModel.getoneLD().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
                binding.tv1.setText("Parameter1: " + v);
            }
        });
        mViewModel.getItemLD().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.tv2.setText("Parameter1: " + s);
            }
        });

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is call the navigation to change the second fragment
                mViewModel.incr_Two();
                mViewModel.setItem("Called by FirstFramgnet");
                Navigation.findNavController(v).navigate(R.id.action_first_to_second);
            }
        });

        return binding.getRoot();
    }
}
