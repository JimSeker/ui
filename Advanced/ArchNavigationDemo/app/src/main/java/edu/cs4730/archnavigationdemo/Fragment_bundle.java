package edu.cs4730.archnavigationdemo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This is a simple fragment to show use to receive a bundle via the nav_graph.  see fragment_two for the
 * bundle creation and send.
 */
public class Fragment_bundle extends Fragment {


    public Fragment_bundle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_bundle, container, false);

        TextView tv_passed = myView.findViewById(R.id.btv_passed);
        TextView tv_passed2 = myView.findViewById(R.id.btv_passed2);

        //via a bundle, instead of safe args
        tv_passed.setText(getArguments().getString("message", "no data"));
        String stuff = "Data 2 is " + getArguments().getInt("number", 1);
        tv_passed2.setText(stuff);
        return myView;
    }

}
