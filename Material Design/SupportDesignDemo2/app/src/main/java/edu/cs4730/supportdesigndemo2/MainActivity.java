package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


/*
 * design tablayout with a viewpager.   So now that the design support better understands
 *  viewpagers, this is much easier and just as simple as just a pagestrip.  See this video
 *  for more useful information:
 *  https://youtu.be/zQekzaAgIlQ
 *
 *  One problem, here is I need to get the background of the tablelayout darker, so you can easily
 *  read the tab names though.
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ViewPager viewPager;
    FragLeft leftfrag;
    FragMid midfrag;
    FragRight rightfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftfrag = new FragLeft();
        midfrag = new FragMid();
        rightfrag = new FragRight();
        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager =  findViewById(R.id.pager);
        ThreeFragmentPagerAdapter adapter = new ThreeFragmentPagerAdapter(fragmentManager);
        viewPager.setAdapter(adapter);

        //new Tablayout from the support design library
        TabLayout mTabLayout = findViewById(R.id.tablayout1);
        mTabLayout.setupWithViewPager(viewPager);

    }

    /*
* We need to extend a FragmentPagerAdapter to add our three fragments.
*   We need to override getCount and getItem.  Also getPageTitle since we are
*   using a PageStripe.
 */
    public class ThreeFragmentPagerAdapter extends FragmentPagerAdapter {
        int PAGE_COUNT = 3;

        //required constructor that simply supers.
        public ThreeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // return the correct fragment based on where in pager we are.
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return leftfrag;
                case 1:
                    return midfrag;
                case 2:
                    return rightfrag;
                default:
                    return null;
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
            return "Page " + String.valueOf(position + 1);

        }

    }

}
