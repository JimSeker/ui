package edu.cs4730.screentest1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


/*
 * This code is purely to see what the android OS picks for dpi.
 * The difference in the code is the pictures that the OS will decide to use. 
 * Otherwise, there is not code in the java.
 * 
 * 
 * See the drawable and layout directories.
 */

public class ScreenTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screentest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screentest, menu);
		return true;
	}

}
