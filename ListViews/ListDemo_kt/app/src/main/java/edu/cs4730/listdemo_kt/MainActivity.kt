package edu.cs4730.listdemo_kt

import java.util.Locale

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"


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
        //mViewPager.setCurrentItem(7);// set to a specific page in the pager.
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> Simple1_ListFragment()
                1 -> Simple2_ListFragment()
                2 -> Simple3_Fragment()
                3 -> InterActive_ListFragment()
                4 -> Phonebook_Fragment()
                else -> Simple1_ListFragment()
            }
        }

        override fun getCount(): Int {
            // Show X total pages.
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence? {
            val l = Locale.getDefault()
            when (position) {
                0 -> return "Simple"
                1 -> return "Simple 2"
                2 -> return "Simple 3"
                3 -> return "InterActive"
                4 -> return "Phone list"
            }
            return null
        }
    }
}