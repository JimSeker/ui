package edu.cs4730.supportdesigndemo2_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * design tablayout with a viewpager.   So now that the design support better understands
 * viewpagers, this is much easier and just as simple as just a pagestrip.  See this video
 * for more useful information:
 * https://youtu.be/zQekzaAgIlQ
 *
 *
 * One problem, here is I need to get the background of the tablelayout darker, so you can easily read it.
 * remove the  <item name="android:textColorSecondary">@color/textColorSecondary</item> from colors.xml
 * and it will default to a more readable color.
 */
class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    lateinit var viewPager: ViewPager2
    lateinit var leftfrag: FragLeft
    lateinit var midfrag: FragMid
    lateinit var rightfrag: FragRight
    lateinit var mTabLayout: TabLayout
    lateinit var mViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //should not be necessary.
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        leftfrag = FragLeft()
        midfrag = FragMid()
        rightfrag = FragRight()
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = ThreeFragmentPagerAdapter(this)

        //new Tablayout from the support design library
        mTabLayout = findViewById(R.id.tab_layout)
        //mTabLayout.setupWithViewPager(viewPager);
        TabLayoutMediator(mTabLayout,
            viewPager
        ) { tab, position ->
            tab.text = "Page " + (position + 1).toString()
        }.attach()
    }

    /**
     * We need to extend a FragmentPagerAdapter to add our three fragments.
     * We need to override getCount and getItem.  Also getPageTitle since we are
     * using a PageStripe.
     */
    inner class ThreeFragmentPagerAdapter  //required constructor that simply supers.
        (fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
        var PAGE_COUNT = 3

        // return the correct fragment based on where in pager we are.
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> leftfrag
                1 -> midfrag
                2 -> rightfrag
                else -> leftfrag //can't be null, so return first one.
            }
        }

        //how many total pages in the viewpager there are.  3 in this case.
        override fun getItemCount(): Int {
            return PAGE_COUNT
        }
    }
}
