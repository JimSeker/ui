package edu.cs4730.frag6demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	String TAG = "MainActivity";
	ViewPager viewPager;
	FragLeft leftfrag;
	FragMid  midfrag;
	FragRight rightfrag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getSupportFragmentManager().beginTransaction().add(R.id.frag_container, firstFragment).commit();
		
		//first find the 3 fragments, if create them as needed.
		//Remember fragments can survive the restart of an activity on orientation change.
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (savedInstanceState != null) {
			leftfrag = (FragLeft) fragmentManager.getFragment(savedInstanceState, "LEFT");
			midfrag  = (FragMid) fragmentManager.getFragment(savedInstanceState,"MIDDLE");
			rightfrag = (FragRight) fragmentManager.getFragment(savedInstanceState,"RIGHT");
			//since survived, remove them from the fragment manager, so don't double add them.
		    FragmentTransaction remove = fragmentManager.beginTransaction();
		    remove.remove(leftfrag);
		    remove.remove(midfrag);
		    remove.remove(rightfrag);
		    if (!remove.isEmpty()) {
		        remove.commit();
		        fragmentManager.executePendingTransactions();
		    }
		} else {
	    	leftfrag = new FragLeft();
	    	midfrag = new FragMid();
	    	rightfrag = new FragRight();
	    }

		viewPager = (ViewPager) findViewById(R.id.pager);
		if (viewPager != null) {
		  //in portrait mode
		  viewPager.setAdapter(new ThreeFragmentPagerAdapter(fragmentManager));
		} else {
			//in landscape mode
		      fragmentManager.beginTransaction()
	          .add(R.id.frag_left, leftfrag, "LEFT")
	          .add(R.id.frag_mid, midfrag, "MIDDLE")
	          .add(R.id.frag_right, rightfrag, "RIGHT")
	          .commit();
		}
	}

	@Override protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
//		if (ViewPager != null) {
//			outState.putInt(KEY_PAGE, pager.getCurrentItem());
//		} else {
//			outState.putInt(KEY_PAGE, lastPage);
//		}
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.putFragment(outState, "LEFT", leftfrag);
	    fragmentManager.putFragment(outState,"MIDDLE", midfrag);
	    fragmentManager.putFragment(outState,"RIGHT", rightfrag);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class ThreeFragmentPagerAdapter extends FragmentPagerAdapter {
		int PAGE_COUNT =3;
		
		public ThreeFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			
			switch (position) {
			  case 0: return leftfrag;
			  case 1: return midfrag;
			  case 2: return rightfrag;
			  default: return null;
			}
		}

		@Override
		public int getCount() {

			return PAGE_COUNT;
		}
	
		//getPageTitle required for the PageStripe to work and have a value.
        @Override
        public CharSequence getPageTitle(int position) {
        	  	
        	//return String.valueOf(position);  //returns string of position for title
        	return "Page "+ String.valueOf(position +1);
        	
        }
		
	}
}
