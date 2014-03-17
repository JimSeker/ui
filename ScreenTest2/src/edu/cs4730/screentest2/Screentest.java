package edu.cs4730.screentest2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Screentest extends Activity {

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
