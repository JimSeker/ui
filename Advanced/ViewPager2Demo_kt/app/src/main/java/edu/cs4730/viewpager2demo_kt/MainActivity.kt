package edu.cs4730.viewpager2demo_kt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    companion object {
        lateinit var leftfrag: FragLeft
        lateinit var midfrag: FragMid
        lateinit var rightfrag: FragRight
    }
    lateinit var tabLayout: TabLayout
    var viewPager: ViewPager2? = null
    lateinit var mViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        leftfrag = FragLeft()
        midfrag = FragMid()
        rightfrag = FragRight()
        val fragmentManager = supportFragmentManager

        viewPager = findViewById(R.id.pager)
        if (viewPager != null) {
            //in portrait mode, so setup the viewpager with the fragments, using the adapter
            viewPager!!.adapter = ThreeFragmentPagerAdapter(this)
            tabLayout = findViewById(R.id.tab_layout)
            TabLayoutMediator(
                tabLayout,
                viewPager!!
            ) { tab, position -> tab.text = "Position " + (position + 1) }.attach()
        } else {
            //in landscape mode  //so no viewpager.
            fragmentManager.beginTransaction()
                .add(R.id.frag_left, leftfrag)
                .add(R.id.frag_mid, midfrag)
                .add(R.id.frag_right, rightfrag)
                .commit()
        }
    }


    /**
     * We need to extend a FragmentPagerAdapter to add our three fragments.
     *   We need to override getCount and getItem.  Also getPageTitle since we are
     *   using a PageStripe.
     */
    class ThreeFragmentPagerAdapter  //required constructor that simply supers.
        (fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
        var PAGE_COUNT = 3

        // return the correct fragment based on where in pager we are.
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> leftfrag
                1 -> midfrag
                2 -> rightfrag
                else -> rightfrag  //won't let me return null, so I guess just reuse last one?
            }
        }

        //how many total pages in the viewpager there are.  3 in this case.
        override fun getItemCount(): Int {
            return PAGE_COUNT
        }
    }
}