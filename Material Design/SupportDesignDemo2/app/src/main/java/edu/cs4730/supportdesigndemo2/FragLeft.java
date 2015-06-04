package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragLeft extends Fragment {
	TextView tx;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Left", "OnCreate");

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("Left", "OnActivityCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("Left", "OnCreateView");
		View view = inflater.inflate(R.layout.left, container, false);
		tx = (TextView) view.findViewById(R.id.tvleft);
		if (savedInstanceState != null) {
			Log.d("Left", "OnCreateView savedInstanceState");
			tx.setText(savedInstanceState.getString("text"));
		}
		return view;
	}
	public void setText(String str) {
		tx.setText(tx.getText() +"\n"+str);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d("Left", "OnSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putString("text", tx.getText().toString());
	}
}
