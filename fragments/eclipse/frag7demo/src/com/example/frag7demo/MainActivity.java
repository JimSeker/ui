package com.example.frag7demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	PageFragment one =null, two =null, three=null, four =null, five = null;
	ViewPager viewPager,viewPager1, viewPager2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    if (savedInstanceState != null) {
	    	one = (PageFragment) fragmentManager.getFragment(savedInstanceState, "ONE");
	    	two = (PageFragment) fragmentManager.getFragment(savedInstanceState, "TWO");
	    	three = (PageFragment) fragmentManager.getFragment(savedInstanceState, "THREE");
	    	four = (PageFragment) fragmentManager.getFragment(savedInstanceState, "FOUR");
	    	five = (PageFragment) fragmentManager.getFragment(savedInstanceState, "FIVE");
	    	//since survived, need to clean up or I can't add them to the pagers adapter again.
		    FragmentTransaction remove = fragmentManager.beginTransaction();
		    remove.remove(one);
		    remove.remove(two);
		    remove.remove(three);
		    remove.remove(four);
		    remove.remove(five);
		    if (!remove.isEmpty()) {
		        remove.commit();
		        fragmentManager.executePendingTransactions();
		    }
	    } else {
	    	one = PageFragment.create(1);
	    	two = PageFragment.create(2);
	    	three = PageFragment.create(3);
	    	four = PageFragment.create(4);
	    	five = PageFragment.create(5);
	    }
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		if (viewPager != null) {
		  //in portrait mode
		  viewPager.setAdapter(new ThreeFragmentPagerAdapter(5));
		} else {
			//in landscape mode
			viewPager1 = (ViewPager) findViewById(R.id.pagerleft);
			viewPager1.setAdapter(new ThreeFragmentPagerAdapter(2));
			viewPager2 = (ViewPager) findViewById(R.id.pagerright);
			viewPager2.setAdapter(new ThreeFragmentPagerAdapter(3));
			
		}
		
	}
	
	
	public class ThreeFragmentPagerAdapter extends FragmentPagerAdapter {
		int PAGE_COUNT;
		
		public ThreeFragmentPagerAdapter(int count) {
			super(getSupportFragmentManager());
			PAGE_COUNT = count;
		}

		@Override
		public Fragment getItem(int position) {
			if (PAGE_COUNT == 3) {
				position = position+ 2;
			}
			switch (position) {
			case 0: return one;
			case 1: return two;
			case 2: return three;
			case 3: return four;
			case 4: return five;
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
	
	@Override protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
	
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.putFragment(outState, "ONE", one);
	    fragmentManager.putFragment(outState,"TWO", two);
	    fragmentManager.putFragment(outState,"THREE", three);
	    fragmentManager.putFragment(outState,"FOUR", four);
	    fragmentManager.putFragment(outState,"FIVE", five);

	}
	
	
	public static class PageFragment extends Fragment {
		public static final String ARG_PAGE = "ARG_PAGE";

		private int mPage;

		public static PageFragment create(int page) {
			Bundle args = new Bundle();
			args.putInt(ARG_PAGE, page);
			PageFragment fragment = new PageFragment();
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mPage = getArguments().getInt(ARG_PAGE);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_page, container, false);
			TextView textView = (TextView) view;
			textView.setText("Fragment #" + mPage);
			return view;
		}
	}
}
