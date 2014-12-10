package edu.cs4730.guidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

/**
 * This shows how the view switcher can work.
 * It's in interesting one, because we can change a "static" layout pretty easy.
 * 
 */
public class ViewSwitch_Fragment extends Fragment {
	String TAG = "ViewSwitch_fragment";
	Context myContext;
	
	//Switcher to change view when input mode changes
	ViewSwitcher myViewSwitch;
	Button myButton;
	
	//ViewFlipper variables.
	ViewFlipper myViewFlipper;
	Button myVFButton;
	Button myVFanim;
	
	public ViewSwitch_Fragment() {
		// Required empty public constructor
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "OnCreate");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.viewswitch_fragment, container, false);
		
		//setup the ViewSwitch variable.
		myViewSwitch = (ViewSwitcher) myView.findViewById(R.id.viewSwitcher1);
		
		//setup the button
		myButton = (Button) myView.findViewById(R.id.vs_button);
        myButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				myViewSwitch.showNext();				
			}
        });

		
		//ViewFlipper code
		myViewFlipper = (ViewFlipper) myView.findViewById(R.id.viewFlipper1);
		//set animation, which can be used in viewswitcher as well
		myViewFlipper.setInAnimation(AnimationUtils.loadAnimation(myContext,android.R.anim.slide_in_left)); //or android.R.anim.fade_in
		myViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(myContext,android.R.anim.slide_out_right));  //or android.R.anim.fade_out
		//setup the button
		myVFButton = (Button) myView.findViewById(R.id.vf_button1);
        myVFButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				myViewFlipper.showNext();				
			}
        });
		
        myVFanim = (Button) myView.findViewById(R.id.vf_button2);
        myVFanim.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if (myViewFlipper.isFlipping()) { //is animated so stop it.
					myViewFlipper.stopFlipping();
					myVFanim.setText("Start animation");
				} else {  //start animation
					myViewFlipper.startFlipping();
					//Can also setup how long, with setFlipInterval( int milliseconds)  appears to be 1 second default.
					myVFanim.setText("Stop animation");
				}			
			}
        });
        
		return myView;
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myContext = activity.getApplicationContext();
		Log.d(TAG,"onAttach");
	}

}
