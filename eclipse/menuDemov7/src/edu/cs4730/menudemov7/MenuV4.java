package edu.cs4730.menudemov7;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MenuV4 extends ActionBarActivity {
	TextView label1, popup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menuv4);
		
		//turn on up button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		label1 = (TextView) findViewById(R.id.label1);
		popup = (TextView) findViewById(R.id.label2);
		popup.setOnClickListener( new OnClickListener(){
			@Override
			public void onClick(View v) {
				showPopupMenu(v); //this is code below, not an API call.
			}
		});
	}

	

	private void showPopupMenu(View v){
			PopupMenu popupM = new PopupMenu(this, v);
			popupM.inflate(R.menu.popup);
			popupM.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_LONG).show();
					label1.append("\n you clicked "+item.toString());
					return true;
				}
			});

			popupM.show();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_v4, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//we use the R.id.x instead of the constants like we use in mainactivity.
		//makes it easy to figure everything else and debug as well.
		switch (item.getItemId()) {
		case android.R.id.home:
			// Navigate "up" the demo structure to the launchpad activity.
			// See http://developer.android.com/design/patterns/navigation.html for more.
			NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
			return true;

		case R.id.item1:
			label1.append("\n You clicked menu item 1");
			return true;
		case R.id.item2:
			label1.append("\n You clicked menu item 2");
			return true;
		case R.id.item3:
			label1.append("\n You clicked menu item 3");
			return true;
		case R.id.item4:
			label1.append("\n You clicked menu item 4");
			return true;
		case R.id.item5:
			label1.append("\n You clicked menu item 5");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
