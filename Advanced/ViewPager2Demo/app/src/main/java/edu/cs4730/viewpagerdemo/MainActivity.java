package edu.cs4730.viewpagerdemo;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

/**
 * this is an example using 3 fragments and a viewpager.
 * In a viewpager, if a fragment is two away from the one displaying, it is sent onDestroyView()
 * so viewmodel has been added to start the data in the fragment, so it will survive an ondestroyview call.
 * the OnsaveInstanceState was not very reliable, but the viewmodel is.
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ViewPager2 viewPager;
    FragLeft leftfrag;
    FragMid midfrag;
    FragRight rightfrag;
    TabLayout tabLayout;

    DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        leftfrag = new FragLeft();
        midfrag = new FragMid();
        rightfrag = new FragRight();
        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager = findViewById(R.id.pager);
        if (viewPager != null) {
            //in portrait mode, so setup the viewpager with the fragments, using the adapter
            viewPager.setAdapter(new ThreeFragmentPagerAdapter(this));
            tabLayout= findViewById(R.id.tab_layout);
            new TabLayoutMediator(tabLayout,
                viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Position " + (position + 1));
                    }
                }
            ).attach();
        } else {
            //in landscape mode  //so no viewpager.
            fragmentManager.beginTransaction()
                .add(R.id.frag_left, leftfrag)
                .add(R.id.frag_mid, midfrag)
                .add(R.id.frag_right, rightfrag)
                .commit();
        }

    }

    /*
     * We need to extend a FragmentPagerAdapter to add our three fragments.
     *   We need to override getCount and getItem.  Also getPageTitle since we are
     *   using a PageStripe.
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
