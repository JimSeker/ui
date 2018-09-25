package edu.cs4730.menudemo;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.app.NavUtils;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AppcompatActivity extends AppCompatActivity {
	PageFragment one =null, two =null, three=null, four =null, five = null;
	ViewPager viewPager;
	myFragmentPagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actionbar);

		//turn on up button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		
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
		mPagerAdapter = new myFragmentPagerAdapter(5);
		viewPager.setAdapter(mPagerAdapter);
		//viewPager.setCurrentItem(2);
		//we need to know when a page has changed, so we can change/fix the next/previous/finish buttons
		viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				supportInvalidateOptionsMenu();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

		menu.findItem(R.id.action_previous).setEnabled(viewPager.getCurrentItem() > 0);
		//help since this attribute is ignored in the xml by api <11.  ie 2.3.3
		//but the change is should xml, use app: instead of android is the ActionBarActivity can read it. 
		//see xml 
		//MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_previous),MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
				(viewPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
				? R.string.action_finish
						: R.string.action_next);
		//item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		MenuItemCompat.setShowAsAction(item,MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// Navigate "up" the demo structure to the launchpad activity.
			// See http://developer.android.com/design/patterns/navigation.html for more.
			NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
			return true;

		case R.id.action_previous:
			// Go to the previous step in the wizard. If there is no previous step,
			// setCurrentItem will do nothing.
			viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
			return true;

		case R.id.action_next:
			// Advance to the next step in the wizard. If there is no next step, setCurrentItem
			// will do nothing.
			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	public class myFragmentPagerAdapter extends FragmentPagerAdapter {
		int PAGE_COUNT;

		public myFragmentPagerAdapter(int count) {
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
