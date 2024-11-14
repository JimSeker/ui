package edu.cs4730.supportdesigndemo2_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import edu.cs4730.supportdesigndemo2_kt.databinding.ActivityMainBinding

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
    lateinit var binding: ActivityMainBinding
    lateinit var leftfrag: FragLeft
    lateinit var midfrag: FragMid
    lateinit var rightfrag: FragRight
    lateinit var mViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //should not be necessary.
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        leftfrag = FragLeft()
        midfrag = FragMid()
        rightfrag = FragRight()

        binding.pager.adapter = ThreeFragmentPagerAdapter(this)

        //new Tablayout from the support design library
        TabLayoutMediator(
            binding.tabLayout, binding.pager
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
