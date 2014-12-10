package edu.cs4730.hidevkbd;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * This code will hide or display the virtual keyboard on the device.
 * It does not work for physical keyboards. you should be able to guess why.
 */


public class HidevkbdActivity extends Activity implements Button.OnClickListener {
	
	Button b1, b2, b3;
	InputMethodManager mgr;
	EditText editText;
	TextView label;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        editText =(EditText) findViewById(R.id.editText1);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        label = (TextView)findViewById(R.id.textView1);
        //get the manager, so I can hide the virtual keyboard.
        mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.button1:
			//first hide the damn virutal keyboard.
			mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			//Now put some stuff in the TextView
			label.setText(editText.getText());
			break;
		case R.id.button2:
			//hide the keyboard.
			mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			break;	
		case R.id.button3:
			//show the keyboard, for no good reason.
			// only will trigger it if no physical keyboard is open
			// This includes simulators that have keyboards.  Works on simulators where there is now keyboard displayed.
			mgr.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
		default:
			break;
		}
		
	}
}