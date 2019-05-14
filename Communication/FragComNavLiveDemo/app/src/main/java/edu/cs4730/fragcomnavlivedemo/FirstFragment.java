package edu.cs4730.fragcomnavlivedemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

/**
 *
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
        mViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        mViewModel.getoneLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
               tv1.setText("Parameter1: " +v);
            }
        });
        mViewModel.getItemLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tv2.setText("Parameter1: " +s);
            }
        });
        tv1 = myView.findViewById(R.id.ff_tv1);

        tv2 = myView.findViewById(R.id.ff_tv2);
        //tv2.setText("Parameter2: " + mParam2);
        btn1 = myView.findViewById(R.id.ff_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is call the navigation to change the second fragment
                mViewModel.incr_Two();
                mViewModel.setItem("Called by FirstFramgnet");
                Navigation.findNavController(v).navigate(R.id.action_first_to_second);
            }
        });

        return myView;
    }
}
