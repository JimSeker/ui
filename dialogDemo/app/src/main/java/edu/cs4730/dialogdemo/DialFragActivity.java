package edu.cs4730.dialogdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DialFragActivity extends FragmentActivity implements
         myDialogFragment.OnDialogFragmentListener, AlertDialogFrag1.OnDialogFragmentListener{

	static final int DIALOG_TYPE_ID = 0;
	static final int DIALOG_GAMEOVER_ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialfragactivity);
        
        
        findViewById(R.id.Button01).setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			showDialog2(DIALOG_GAMEOVER_ID);
    		}

        });
        findViewById(R.id.Button02).setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v) {            
    			showDialog2(DIALOG_TYPE_ID);
    		}
        });
        findViewById(R.id.Button03).setOnClickListener(new OnClickListener(){
    		@Override
    		public void onClick(View v) {
    			showDialog1();
    		}
        });
    }

	void showDialog1() {
		FragmentManager fm = getSupportFragmentManager();
		AlertDialogFrag1 newFragment = AlertDialogFrag1.newInstance(R.string.alert_dialog_two_buttons_title);
	    newFragment.show(fm, "dialog");
	}
	
	void showDialog2(int which) {
		FragmentManager fm = getSupportFragmentManager();
		myDialogFragment newFragment = myDialogFragment.newInstance(which);
	    newFragment.show(fm, "myDialog");
	}


    /*
    *    These three methods are the callback methods for the dialog fragment callbacks.
    *    note doPositiveClick and doNegativeClick are for both AlertDialogFrag1, while doItem
    *    is only for the myDialogFragment listener.
    */
	public void doPositiveClick() {
	    // Do stuff here.
		Toast.makeText(getApplicationContext(),"Positive/Yes click!" , Toast.LENGTH_SHORT).show();
	    //Log.i("FragmentAlertDialog", "Positive click!");
	}

	public void doNegativeClick() {
	    // Do stuff here.
		Toast.makeText(getApplicationContext(),"Negative/No/Cancel click!" , Toast.LENGTH_SHORT).show();
	    //Log.i("FragmentAlertDialog", "Negative/Cancel click!");
	}
	public void doItem(String item) {
		Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
	}
}
