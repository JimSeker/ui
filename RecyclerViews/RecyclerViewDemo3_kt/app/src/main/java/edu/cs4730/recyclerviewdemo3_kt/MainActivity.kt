package edu.cs4730.recyclerviewdemo3_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.cs4730.recyclerviewdemo3_kt.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() {
    lateinit var mSectionsPagerAdapter: SectionsPagerAdapter
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(this)

        // Set up the ViewPager with the sections adapter.

        binding.pager.adapter = mSectionsPagerAdapter
        //now setup the headers for it.
        TabLayoutMediator(
            binding.tabLayout,
            binding.pager
        ) { tab, position ->
            val l = Locale.getDefault()
            when (position) {
                0 -> tab.text = getString(R.string.title_section1).uppercase(l)
                1 -> tab.text = getString(R.string.title_section2).uppercase(l)
                2 -> tab.text = getString(R.string.title_section3).uppercase(l)
                3 -> tab.text = getString(R.string.title_section4).uppercase(l)
                4 -> tab.text = getString(R.string.title_section5).uppercase(l)
            }
        }.attach()
    }

    /**
     * A [FragmentStateAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fa: FragmentActivity) :
        FragmentStateAdapter(fa) {
        var PAGE_COUNT = 5
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> Simple1_Fragment()
                1 -> Simple2_Fragment()
                2 -> Simple3_Fragment()
                3 -> InterActive_Fragment()
                4 -> Phonebook_Fragment()
                else -> Simple1_Fragment()  //default, since it won't null.

            }
        }

        override fun getItemCount(): Int {
            return PAGE_COUNT
        }
    }
}
