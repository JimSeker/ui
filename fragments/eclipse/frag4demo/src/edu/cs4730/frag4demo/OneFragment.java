package edu.cs4730.frag4demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link OneFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link OneFragment#newInstance} factory method to create an instance of this
 * fragment.
 * 
 */
public class OneFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	TextView tv1, tv2;
	Button btn1;
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment OneFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static OneFragment newInstance(String param1, String param2) {
		OneFragment fragment = new OneFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public OneFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_one, container, false);
		tv1 = (TextView) myView.findViewById(R.id.of_tv1);
		tv1.setText("Parameter1: "+mParam1);
		tv2 = (TextView) myView.findViewById(R.id.of_tv2);
		tv2.setText("Parameter2: "+mParam2);
		btn1 = (Button) myView.findViewById(R.id.of_btn1);
		btn1.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					//this is calling the interface, which call into the activity, so it 
					//can change to the first fragment and send a simple string as well.
					mListener.onFragmentInteraction();
				}
			}
		});
		
		
		return myView;
	}
/*
		if (mListener != null) {
			mListener.onFragmentInteraction();
		}
*/
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction();
	}

}
