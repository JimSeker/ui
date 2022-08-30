package edu.cs4730.menudemo_kt

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

/**
 * This example shows how to use a viewpager2 with button menu items to change the pager.
 *
 */
class ViewPagerButtonMenuActivity : AppCompatActivity() {
    lateinit var one: PageFragment
    lateinit var two: PageFragment
    lateinit var three: PageFragment
    lateinit var four: PageFragment
    lateinit var five: PageFragment
    lateinit var viewPager: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var mPagerAdapter: myFragmentPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpagerbuttonmenu)

        //turn on up button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val fragmentManager = supportFragmentManager
        if (savedInstanceState != null) {
            one = fragmentManager.getFragment(savedInstanceState, "ONE") as PageFragment
            two = fragmentManager.getFragment(savedInstanceState, "TWO") as PageFragment
            three = fragmentManager.getFragment(savedInstanceState, "THREE") as PageFragment
            four = fragmentManager.getFragment(savedInstanceState, "FOUR") as PageFragment
            five = fragmentManager.getFragment(savedInstanceState, "FIVE") as PageFragment
            //since survived, need to clean up or I can't add them to the pagers adapter again.
            val remove = fragmentManager.beginTransaction()
            remove.remove(one)
            remove.remove(two)
            remove.remove(three)
            remove.remove(four)
            remove.remove(five)
            if (!remove.isEmpty) {
                remove.commit()
                fragmentManager.executePendingTransactions()
            }
        } else {
            one = PageFragment.create(1)
            two = PageFragment.create(2)
            three = PageFragment.create(3)
            four = PageFragment.create(4)
            five = PageFragment.create(5)
        }
        viewPager = findViewById(R.id.pager)
        mPagerAdapter = myFragmentPagerAdapter(this)
        viewPager.adapter = mPagerAdapter
        //viewPager.setCurrentItem(2);
        //we need to know when a page has changed, so we can change/fix the next/previous/finish buttons
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                supportInvalidateOptionsMenu()
            }
        })
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE //dont show all the tabs, which is MODE_FIXED.
        TabLayoutMediator(tabLayout,
            viewPager,
            TabConfigurationStrategy { tab, position ->
                tab.text = "This Page is " + (position + 1)
            }
        ).attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_screen_slide, menu)
        menu.findItem(R.id.action_previous).isEnabled = viewPager.currentItem > 0
        //help since this attribute is ignored in the xml by api <11.  ie 2.3.3
        //but the change is should xml, use app: instead of android is the ActionBarActivity can read it.
        //see xml
        //MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_previous),MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        val item = menu.add(
            Menu.NONE, R.id.action_next, Menu.NONE,
            if (viewPager.currentItem == mPagerAdapter.itemCount - 1) R.string.action_finish else R.string.action_next
        )
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM or MenuItem.SHOW_AS_ACTION_WITH_TEXT)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // Navigate "up" the demo structure to the launchpad activity.
            // See http://developer.android.com/design/patterns/navigation.html for more.
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        } else if (item.itemId == R.id.action_previous) {
            // Go to the previous step in the wizard. If there is no previous step,
            // setCurrentItem will do nothing.
            viewPager.currentItem = viewPager.currentItem - 1
            return true
        } else if (item.itemId == R.id.action_next) {
            // Advance to the next step in the wizard. If there is no next step, setCurrentItem
            // will do nothing.
            viewPager.currentItem = viewPager.currentItem + 1
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    inner class myFragmentPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(
        fa
    ) {
        var PAGE_COUNT = 5
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> one
                1 -> two
                2 -> three
                3 -> four
                4 -> five
                else -> one //because can't return null.
            }
        }

        override fun getItemCount(): Int {
            return PAGE_COUNT
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val fragmentManager = supportFragmentManager
        fragmentManager.putFragment(outState, "ONE", one)
        fragmentManager.putFragment(outState, "TWO", two)
        fragmentManager.putFragment(outState, "THREE", three)
        fragmentManager.putFragment(outState, "FOUR", four)
        fragmentManager.putFragment(outState, "FIVE", five)
    }

    class PageFragment : Fragment() {
        private var mPage = 0
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            mPage = requireArguments().getInt(ARG_PAGE)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_page, container, false)
            val textView = view as TextView
            textView.text = "Fragment #$mPage"
            return view
        }

        companion object {
            const val ARG_PAGE = "ARG_PAGE"
            fun create(page: Int): PageFragment {
                val args = Bundle()
                args.putInt(ARG_PAGE, page)
                val fragment = PageFragment()
                fragment.arguments = args
                return fragment
            }
        }
    }
}