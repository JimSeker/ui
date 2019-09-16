package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * This is the middle fragment, which uses a edittext to put text on the either the left or right
 * fragment in the view pager. It uses a modelview to start the data so the fragments can get via
 * an observer.
 */

public class FragMid extends Fragment implements Button.OnClickListener {
    Button btn_lt, btn_rt;
    EditText et;
    MainActivity parent;
    DataViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.middle, container, false);
        btn_lt = view.findViewById(R.id.btn_lt);
        btn_lt.setOnClickListener(this);
        btn_rt = view.findViewById(R.id.btn_rt);
        btn_rt.setOnClickListener(this);
        et = view.findViewById(R.id.editText1);
        parent = (MainActivity) getActivity();
        mViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_lt) { //left button
            mViewModel.appendStrLeft(et.getText().toString());
        } else {  //right button
            mViewModel.appendStrRight(et.getText().toString());
        }
        et.setText("");
    }
}
