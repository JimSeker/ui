package edu.cs4730.listdemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import edu.cs4730.listdemo_kt.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        // Set up the ViewPager with the sections adapter.
        binding.pager.setAdapter(SectionsPagerAdapter(this))
        //now setup the tablelayout titles.
        TabLayoutMediator(
            binding.tabLayout, binding.pager
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

     */
    class SectionsPagerAdapter(fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
        private var PAGE_COUNT = 5
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> Simple1_ListFragment()
                1 -> Simple2_ListFragment()
                2 -> Simple3_Fragment()
                3 -> InterActive_ListFragment()
                4 -> Phonebook_Fragment()
                else -> Simple1_ListFragment()
            }
        }

        override fun getItemCount(): Int {
            // Show X total pages.
            return PAGE_COUNT
        }
    }
}