package edu.cs4730.viewpager2demo;

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
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import edu.cs4730.viewpager2demo.databinding.ActivityMainBinding;

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
    //TabLayout tabLayout;

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
            binding.pager.setAdapter(new ThreeFragmentPagerAdapter(this));

            new TabLayoutMediator(binding.tabLayout,
                    binding.pager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Position ".concat (String.valueOf(position + 1)));
                    }
                }
            ).attach();
        } else {
            //in landscape mode  //so no viewpager.
            fragmentManager.beginTransaction()
                .add(binding.fragLeft.getId(), leftfrag)
                .add(binding.fragMid.getId(), midfrag)
                .add(binding.fragRight.getId(), rightfrag)
                .commit();
        }

    }

    /**
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
                    return rightfrag;  //should be null, but that would cause the app to blow up.
            }
        }

        //how many total pages in the viewpager there are.  3 in this case.
        @Override
        public int getItemCount() {

            return PAGE_COUNT;
        }


    }
}
