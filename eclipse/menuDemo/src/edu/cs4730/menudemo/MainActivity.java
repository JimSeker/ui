package edu.cs4730.menudemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/*
 * This code for the menus should work on any android device.
 * It uses the older API 1 version to create menus.  
 */
public class MainActivity extends Activity {
	 protected static final int Menu1_ID = Menu.FIRST;
	 protected static final int Menu2_ID = Menu.FIRST+1;
	 protected static final int Menu3_ID = Menu.FIRST+2;
	 protected static final int Menu4_ID = Menu.FIRST+3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0,  Menu1_ID, 0, "Menu V4 demo");
		menu.add(0,  Menu2_ID, 0, "Menu and Fragments demo");
		menu.add(0,  Menu3_ID, 0, "ActionBar action Items demo");
		menu.add(0,  Menu4_ID, 0, "ActionBar buttons demo");
		return super.onCreateOptionsMenu(menu);

	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu1_ID:
			startActivity(new Intent(MainActivity.this, menuV4.class));
			return true;
		case Menu2_ID:
			startActivity(new Intent(MainActivity.this, FragMenuActivity.class));
			return true;
		case Menu3_ID:
			startActivity(new Intent(MainActivity.this, ActionMenuActivity.class));
			return true;
		case Menu4_ID:
			startActivity(new Intent(MainActivity.this, ActionbarActivity.class));
			return true;
			
		default:
		  return super.onOptionsItemSelected(item);
		}
	}
}
