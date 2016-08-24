package edu.cs4730.guidemo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * Example of Radio button 
 * and check box.
 * 
 */
public class RadioCheck_Fragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    String TAG = "RadioCheck_Fragment";
    Context myContext;   //needed for a number of things, but mostly for toast, set in onAttach.
    
	RadioGroup myRadioGroup;
    CheckBox myCheckBox;
    
	public RadioCheck_Fragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.radiocheck_fragment, container, false);
		
		//setup the radiogroup.
		myRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup1);  //get the radio group
		  //note, we didn't get the individual radio Buttons, we don't normally need them.
		  //listener is onCheckedChanged and implemented from class level.
		myRadioGroup.setOnCheckedChangeListener(this);
		
		//setup the check box.
		myCheckBox = (CheckBox) view.findViewById(R.id.checkBox1);
		myCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
				//We know which checkbox, so ignoring buttonView variable.
				if (isChecked) {
					myCheckBox.setText("Uncheck me and I'll clear the raido Buttons too!");
				} else {
					myRadioGroup.clearCheck();  //clears any radio buttons.
					myCheckBox.setText("Please Check me!");
				}
			}
			
		});
		
		return view;
	}


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		myContext = context;
		Log.d(TAG,"onAttach");
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
	 	if (group == myRadioGroup) { //if not, we are in trouble!
	     if (checkedId == R.id.radio0) {
	         // information radio button clicked
	    	 Toast.makeText(myContext, "Radio Button 0 checked.", Toast.LENGTH_SHORT).show();
	         } else if (checkedId == R.id.radio1) {
	         // Confirmation radio button clicked
	        	 Toast.makeText(myContext, "Radio Button 1 checked.", Toast.LENGTH_SHORT).show();
	         } else if (checkedId == R.id.radio2) {
	         //radio button 2 clicked
	        	 Toast.makeText(myContext, "Radio Button 2 checked.", Toast.LENGTH_SHORT).show();
	         }
	 	}
	}

}
