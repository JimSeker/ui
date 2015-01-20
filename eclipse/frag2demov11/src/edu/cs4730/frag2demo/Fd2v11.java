package edu.cs4730.frag2demo;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;


public class Fd2v11 extends Activity implements titlefrag.Callbacks {
   boolean TwoPane = false;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. 
        if (findViewById(R.id.frag_container) == null ) {
        	TwoPane = true;
        }
        
	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(int id) {
		if (TwoPane) {
			Log.d("fd2", "two pane");
			//The activity has access to the fragment, so we will just call a method in the fragment.
			textFrag tf = (textFrag) getFragmentManager().findFragmentById(R.id.frag_text);
			tf.setText(id);	
			//((textFrag) getSupportFragmentManager().findFragmentById(R.id.frag_text)
			//		).setText(id);
		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Log.d("fd2", "one pane");
			Intent detailIntent = new Intent(this, textActivity.class);
			detailIntent.putExtra(textFrag.ARG_TEXT_ID, id);
			startActivity(detailIntent);
		}
	}
}
