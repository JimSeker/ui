package edu.cs4730.FormExample;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
 * This is a simple example code to demonstrate some simple views.
 */

public class FormExample extends Activity implements RadioGroup.OnCheckedChangeListener, TextWatcher, 
            Button.OnClickListener {
    /** Called when the activity is first created. */
	RadioGroup myRadioGroup;
	EditText et; 
    Button btnalert;
    TextView label ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //EditText view setup and listner
        et = (EditText) findViewById(R.id.ETname);
        et.addTextChangedListener(this);

        //the top label in the xml doc.
        label = (TextView) findViewById(R.id.Label01);

        //setup the radio group with a listener.
        myRadioGroup = (RadioGroup)findViewById(R.id.SndGroup);
        myRadioGroup.setOnCheckedChangeListener(this);

        //setup the button with a listener as well.
       btnalert = (Button) findViewById(R.id.Button01);
        btnalert.setOnClickListener(this);

        
    }
	
    /*  Radio group listener for OnCheckedChangeListener */
    public void onCheckedChanged(RadioGroup group, int CheckedId) {
    	if (group == myRadioGroup) { //if not myRadioGroup, we are in trouble!
    		if (CheckedId == R.id.RB01) {
    			// information radio button clicked
    		} else if (CheckedId == R.id.RB02) {
    			// Confirmation radio button clicked
    		} else if (CheckedId == R.id.RB03) {
    			// Warning radio button clicked
                Toast.makeText(this, "Warning!", Toast.LENGTH_LONG).show();
    		}
    	}
    }
    /* EditView listeners */
    public void onTextChanged (CharSequence s, int start, int before, int count) {
    	if (et.length() >10) {
            Toast.makeText(this, "Long Word!", Toast.LENGTH_SHORT).show();
    	}
    }
    public void beforeTextChanged( CharSequence s, int start, int count, int after) {
        //left blank
    }
    public void afterTextChanged(Editable s) {
    	//left blank
    }

    /* button listener */
    public void onClick(View v) {
    	if (v == btnalert) {
            Toast.makeText(this, "The button was pressed",Toast.LENGTH_SHORT).show();
        }

    }

    


}  
