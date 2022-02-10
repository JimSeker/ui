package edu.cs4730.navdrawerfragviewmodeldemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * This is a simple fragment used to display the data for whichever shakespeare that is clicked on
 * in the titlefrag fragment.  it uses a viewmodel, so everything is received from there.
 */

public class textFrag extends Fragment {

    TextView tv;
    DataViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.text_fragment, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        tv = view.findViewById(R.id.text);
        mViewModel.getData().observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv.setText(Shakespeare.DIALOGUE[integer]);
            }
        });
        return view;
    }
}
