package edu.cs4730.frag1demo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class textFrag extends Fragment {
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
		View view = inflater.inflate(R.layout.text, container, false);
		return view;
	}
	
	/*
	 * simple method to set the text of the TextView from the layout, called from the TitleFrag.
	 */
	public void setText(String item) {
		TextView tv = (TextView) getView().findViewById(R.id.text);
		tv.setText(item);
		}
}
