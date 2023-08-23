package edu.cs4730.fragcomnavvmodeldemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.cs4730.fragcomnavvmodeldemo.databinding.FragmentMainBinding;

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
public class MainFragment extends Fragment {
    FragmentMainBinding binding;
    DataViewModel mViewModel;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        //button to call firstfragment
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the navigation to call the action to change to first fragment.
                //can change to the first fragment and send a simple string as well.
                mViewModel.num_one++;
                mViewModel.setItem("Called From MainFrag");
                Navigation.findNavController(v).navigate(R.id.action_main_to_first);
            }
        });
        //button to call secondfragment.
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the navigation to call the action to change to second fragment.
                mViewModel.num_two++;
                mViewModel.setItem("Called From MainFrag");
                Navigation.findNavController(v).navigate(R.id.action_main_to_second);
            }
        });

        binding.tv1.setText("Parameter1: " + mViewModel.num_one);
        binding.tv2.setText("Parameter2: " + mViewModel.num_two);
        return binding.getRoot();
    }


}