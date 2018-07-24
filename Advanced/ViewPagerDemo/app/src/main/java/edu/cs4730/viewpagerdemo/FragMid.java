package edu.cs4730.viewpagerdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragMid extends Fragment implements Button.OnClickListener {
	Button btn_lt, btn_rt;
	EditText et;
	MainActivity parent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.middle, container, false);
		btn_lt = view.findViewById(R.id.btn_lt);
		btn_lt.setOnClickListener(this);
		btn_rt = view.findViewById(R.id.btn_rt);
		btn_rt.setOnClickListener(this);
		et = view.findViewById(R.id.editText1);
		parent = (MainActivity)getActivity();
		
		return view;
	}

	@Override
	public void onClick(View v) {
		if (v == btn_lt) { //left button
			parent.leftfrag.setText(et.getText().toString());
		} else {  //right button
			parent.rightfrag.setText(et.getText().toString());
		}
		et.setText("");
	}
}
