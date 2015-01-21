package edu.cs4730.menudemov7;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menuv4demo) {
			startActivity(new Intent(MainActivity.this, MenuV4.class));
			return true;
		} else if (id == R.id.menuandfragdemo) {
			startActivity(new Intent(MainActivity.this, FragMenuActivity.class));
			return true;
		}else if (id == R.id.actbaritemsdemo) {
			startActivity(new Intent(MainActivity.this, ActionMenuActivity.class));
			return true;
		}else if (id == R.id.Actionbuttonsdemo) {
			startActivity(new Intent(MainActivity.this, ActionbarActivity.class));
			return true;
		}else if (id == R.id.Actionsearchdemo) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
