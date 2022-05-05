package edu.cs4730.archnavigationdemo;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This example receives arguments via the safe_arg version.  Note safe args are included in Project build.grade (not module).
 */

public class Fragment_Three extends Fragment {


    public Fragment_Three() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment__three, container, false);
        TextView tv_passed = myView.findViewById(R.id.tv_passed);
        tv_passed.setText(Fragment_ThreeArgs.fromBundle(getArguments()).getMessage());
        TextView tv_passed2 = myView.findViewById(R.id.tv_passed2);
        String stuff = "Data 2 is " + Fragment_ThreeArgs.fromBundle(getArguments()).getNumber();
        tv_passed2.setText(stuff);
        return myView;
    }

}

