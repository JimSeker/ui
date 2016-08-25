package edu.cs4730.fragcomdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class MainFragment extends Fragment {

	private OnFragmentInteractionListener mListener;

	Button btn1, btn2;
	
	public MainFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_main, container, false);
		
		//button to call firstfragment
		btn1 = (Button) myView.findViewById(R.id.button1);
		btn1.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					//this is calling the interface, which call into the activity, so it 
					//can change to the first fragment and send a simple string as well.
					mListener.onFragmentInteraction(1);
				}
			}
		});
		//button to call secondfragment.
		btn2 = (Button) myView.findViewById(R.id.button2);
		btn2.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					//this is calling the interface, which call into the activity, so it 
					//can change to the second fragment and send a simple string as well.
					mListener.onFragmentInteraction(2);
				}
			}
		});
		return myView;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Activity activity = getActivity();
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
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		void onFragmentInteraction(int which);
	}
}
