package edu.cs4730.frag3demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

public class MainActivity  extends FragmentActivity implements titlefrag.Callbacks {
	boolean TwoPane = false;
	titlefrag firstFragment;
	textFrag secondFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	       // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.frag_container) == null ) {
        	//landscape or large mode.  both fragments will be displayed on the screen.
        	Log.d("fd3", "two pane");
        	TwoPane = true;
        } else { 
          //protrait mode
        	Log.d("fd3", "one pane");
         //get them if they already exist else create them.
        	firstFragment = (titlefrag) getSupportFragmentManager().findFragmentByTag("first");
        	if (firstFragment == null) {
        		firstFragment = new titlefrag();
        	}
        	secondFragment = (textFrag) getSupportFragmentManager().findFragmentByTag("Second");
        	if (secondFragment == null) {
        		secondFragment = new textFrag();
    	        Bundle args = new Bundle();
    	        args.putInt("position",  0);
    	        secondFragment.setArguments(args);
        	}
        	FragmentManager fragmentManager = getSupportFragmentManager();
		    FragmentTransaction remove = fragmentManager.beginTransaction();
		    remove.remove(firstFragment);
		    remove.remove(secondFragment);
		    if (!remove.isEmpty()) {
		        remove.commit();
		        fragmentManager.executePendingTransactions();
		    }
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
//        	if (savedInstanceState != null) {
//                return; 
//            }
            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            fragmentManager.beginTransaction().replace(R.id.frag_container, firstFragment, "first").commit();
        }
	}
	
	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(int id) {
		if (TwoPane) {
			Log.d("fd3", "two pane");
			//The activity has access to the fragment, so we will just call a method in the fragment.
			//textFrag tf = (textFrag) getFragmentManager().findFragmentById(R.id.frag_text);
			//tf.setText(id);	
			((textFrag) getSupportFragmentManager().findFragmentById(R.id.frag_text)
					).setText(id);
		} else {
			Log.d("fd3", "one pane");
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
//			Log.d("fd2", "one pane");
//			Intent detailIntent = new Intent(this, textActivity.class);
//			detailIntent.putExtra(textFrag.ARG_TEXT_ID, id);
//			startActivity(detailIntent);
			
			//set the position correctly.  We defaulted it to zero.
	        Bundle args = new Bundle();
	        args.putInt("position",  id);
	        secondFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack so the user can navigate back
			transaction.replace(R.id.frag_container, secondFragment, "second" );
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		}
	}
}
