package edu.cs4730.guidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Relativelayout_Fragment extends Fragment {
	
	
    String TAG = "Relativelayout_Fragment";
    Context myContext;

	public Relativelayout_Fragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState )	{
		View myView;
		myView = inflater.inflate( R.layout.relativelayout_fragment, container, false );
		return myView;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myContext = activity.getApplicationContext();
		Log.d(TAG,"onAttach");
	}

	

}
