package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/*
 * design tablayout with a viewpager.   Personally I think the pagerstrip is the better choice, but android...
 *  This uses the viewpager example from the advanced directory.  Removes the page strip and uses the
 *  tablayout instead.   We then need to connect the viewpager with the tablayout in the java code below.
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ViewPager viewPager;
    FragLeft leftfrag;
    FragMid  midfrag;
    FragRight rightfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftfrag = new FragLeft();
        midfrag = new FragMid();
        rightfrag = new FragRight();
        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager = (ViewPager) findViewById(R.id.pager);
        ThreeFragmentPagerAdapter adapter = new ThreeFragmentPagerAdapter(fragmentManager);
        viewPager.setAdapter(adapter);

        //new Tablayout from the support design library
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tablayout1);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        //swipeing the viewpager will now change the tabs
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        //we ned to add a listener, otherwise the user selects the tab and the tab changes, but not
        //viewpager.
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /*
* We need to extend a FragmentPagerAdapter to add our three fragments.
*   We need to override getCount and getItem.  Also getPageTitle since we are
*   using a PageStripe.
 */
    public class ThreeFragmentPagerAdapter extends FragmentPagerAdapter {
        int PAGE_COUNT =3;

        //required constructor that simply supers.
        public ThreeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // return the correct fragment based on where in pager we are.
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: return leftfrag;
                case 1: return midfrag;
                case 2: return rightfrag;
                default: return null;
            }
        }

        //how many total pages in the viewpager there are.  3 in this case.
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
