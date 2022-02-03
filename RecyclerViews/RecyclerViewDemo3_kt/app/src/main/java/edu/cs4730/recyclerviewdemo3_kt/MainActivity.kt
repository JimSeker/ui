package edu.cs4730.recyclerviewdemo3_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var mSectionsPagerAdapter: SectionsPagerAdapter
    lateinit var mViewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById<View>(R.id.pager) as ViewPager
        mViewPager.adapter = mSectionsPagerAdapter
        //mViewPager.setCurrentItem(4);// set to a specific page in the pager.
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> Simple1_Fragment()
                1 -> Simple2_Fragment()
                2 -> Simple3_Fragment()
                3 -> InterActive_Fragment()
                4 -> Phonebook_Fragment()
                else -> Simple1_Fragment()
            }
        }

        override fun getCount(): Int {
            // Show X total pages.  which is 1+ the case in getItem.
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence? {
            val l = Locale.getDefault()
            when (position) {
                0 -> return getString(R.string.title_section1).uppercase(l)
                1 -> return getString(R.string.title_section2).uppercase(l)
                2 -> return getString(R.string.title_section3).uppercase(l)
                3 -> return getString(R.string.title_section4).uppercase(l)
                4 -> return getString(R.string.title_section5).uppercase(l)
            }
            return null
        }
    }
}