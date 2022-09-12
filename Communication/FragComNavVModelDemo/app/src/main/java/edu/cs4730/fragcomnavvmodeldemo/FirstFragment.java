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

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
public class FirstFragment extends Fragment {


    TextView tv1, tv2;
    Button btn1;
    DataViewModel mViewModel;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_first, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        tv1 = myView.findViewById(R.id.ff_tv1);
        tv2 = myView.findViewById(R.id.ff_tv2);
        btn1 = myView.findViewById(R.id.ff_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is call the navigation to change the second fragment
                mViewModel.num_two++;
                mViewModel.setItem("Called by FirstFragment");
                Navigation.findNavController(v).navigate(R.id.action_first_to_second);
            }
        });
        mViewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                tv2.setText("Parameter2: " + data);
            }
        });

        tv1.setText("Parameter1: " + mViewModel.num_one);
        return myView;
    }

}
