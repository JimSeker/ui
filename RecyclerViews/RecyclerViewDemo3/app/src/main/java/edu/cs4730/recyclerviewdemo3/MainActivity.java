package edu.cs4730.recyclerviewdemo3;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import edu.cs4730.recyclerviewdemo3.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ActivityMainBinding binding;

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
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this);

        // Set up the ViewPager with the sections adapter.
        binding.pager.setAdapter(mSectionsPagerAdapter);
        //now setup the headers for it.
        new TabLayoutMediator(binding.tabLayout,
            binding.pager,
            new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    Locale l = Locale.getDefault();
                    switch (position) {
                        case 0:
                            tab.setText(getString(R.string.title_section1).toUpperCase(l));
                            break;
                        case 1:
                            tab.setText(getString(R.string.title_section2).toUpperCase(l));
                            break;
                        case 2:
                            tab.setText(getString(R.string.title_section3).toUpperCase(l));
                            break;
                        case 3:
                            tab.setText(getString(R.string.title_section4).toUpperCase(l));
                            break;
                        case 4:
                            tab.setText(getString(R.string.title_section5).toUpperCase(l));
                            break;

                    }
                }
            }
        ).attach();
    }

    /**
     * A {@link FragmentStateAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStateAdapter {
        int PAGE_COUNT = 5;

        public SectionsPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new Simple1_Fragment();
                case 1:
                    return new Simple2_Fragment();
                case 2:
                    return new Simple3_Fragment();
                case 3:
                    return new InterActive_Fragment();
                case 4:
                    return new Phonebook_Fragment();
                default:
                    return new Simple1_Fragment();  //default.
            }
        }

        @Override
        public int getItemCount() {
            // Show X total pages.  which is 1+ the case in getItem.
            return PAGE_COUNT;
        }
    }

}
