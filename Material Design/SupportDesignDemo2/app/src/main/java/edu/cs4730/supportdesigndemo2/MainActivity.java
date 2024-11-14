package edu.cs4730.supportdesigndemo2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import edu.cs4730.supportdesigndemo2.databinding.ActivityMainBinding;


/**
 * design tablayout with a viewpager.   So now that the design support better understands
 * viewpagers, this is much easier and just as simple as just a pagestrip.  See this video
 * for more useful information:
 * https://youtu.be/zQekzaAgIlQ
 * <p>
 * One problem, here is I need to get the background of the tablelayout darker, so you can easily read it.
 * remove the  <item name="android:textColorSecondary">@color/textColorSecondary</item> from colors.xml
 * and it will default to a more readable color.
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
        //should not be necessary.
        mViewModel = new ViewModelProvider(this).get(DataViewModel.class);

        leftfrag = new FragLeft();
        midfrag = new FragMid();
        rightfrag = new FragRight();


        binding.pager.setAdapter(new ThreeFragmentPagerAdapter(this));

        //new Tablayout from the support design library
        //mTabLayout.setupWithViewPager(viewPager);
        new TabLayoutMediator(binding.tabLayout, binding.pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Page " + String.valueOf(position + 1));
            }
        }).attach();
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
        @NonNull
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
                    return leftfrag; //can't be null, so return first one.
            }
        }

        //how many total pages in the viewpager there are.  3 in this case.
        @Override
        public int getItemCount() {
            return PAGE_COUNT;
        }


    }

}
