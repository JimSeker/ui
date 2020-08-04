package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


/**
 * design tablayout with a viewpager.   So now that the design support better understands
 * viewpagers, this is much easier and just as simple as just a pagestrip.  See this video
 * for more useful information:
 * https://youtu.be/zQekzaAgIlQ
 * <p>
 * One problem, here is I need to get the background of the tablelayout darker, so you can easily
 * read the tab names though.
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ViewPager2 viewPager;
    FragLeft leftfrag;
    FragMid midfrag;
    FragRight rightfrag;
    TabLayout mTabLayout;
    DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //should not be necessary.
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        leftfrag = new FragLeft();
        midfrag = new FragMid();
        rightfrag = new FragRight();

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new ThreeFragmentPagerAdapter(this));

        //new Tablayout from the support design library
        mTabLayout = findViewById(R.id.tab_layout);
        //mTabLayout.setupWithViewPager(viewPager);
        new TabLayoutMediator(mTabLayout,
                viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Page " + String.valueOf(position + 1));
                    }
                }
        ).attach();
    }

    /**
     * We need to extend a FragmentPagerAdapter to add our three fragments.
     * We need to override getCount and getItem.  Also getPageTitle since we are
     * using a PageStripe.
     */
    public class ThreeFragmentPagerAdapter extends FragmentStateAdapter {
        int PAGE_COUNT = 3;

        //required constructor that simply supers.
        public ThreeFragmentPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        // return the correct fragment based on where in pager we are.
        @Override
        public Fragment createFragment(int position) {

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
        public int getItemCount() {
            return PAGE_COUNT;
        }


    }

}
