package edu.cs4730.viewpagerdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import edu.cs4730.viewpagerdemo.databinding.ActivityMainBinding;

/**
 * this is an example using 3 fragments and a viewpager.
 * In a viewpager, if a fragment is two away from the one displaying, it is sent onDestroyView()
 * so viewmodel has been added to start the data in the fragment, so it will survive an ondestroyview call.
 * the OnsaveInstanceState was not very reliable, but the viewmodel is.
 */

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ActivityMainBinding binding;
    FragLeft leftfrag;
    FragMid midfrag;
    FragRight rightfrag;

    DataViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        leftfrag = new FragLeft();
        midfrag = new FragMid();
        rightfrag = new FragRight();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (binding.pager != null) {
            //in portrait mode, so setup the viewpager with the fragments, using the adapter
            binding.pager.setAdapter(new ThreeFragmentPagerAdapter(fragmentManager));
        } else {
            //in landscape mode  //so no viewpager.
            fragmentManager.beginTransaction()
                    .add(R.id.frag_left, leftfrag)
                    .add(R.id.frag_mid, midfrag)
                    .add(R.id.frag_right, rightfrag)
                    .commit();
        }
    }

    /**
     * We need to extend a FragmentPagerAdapter to add our three fragments.
     * We need to override getCount and getItem.  Also getPageTitle since we are
     * using a PageStripe.
     */
    public class ThreeFragmentPagerAdapter extends FragmentPagerAdapter {
        int PAGE_COUNT = 3;

        //required constructor that simply supers.
        public ThreeFragmentPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        // return the correct fragment based on where in pager we are.
        @NonNull
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
                    return rightfrag;  //should be null, but then it would die.
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
