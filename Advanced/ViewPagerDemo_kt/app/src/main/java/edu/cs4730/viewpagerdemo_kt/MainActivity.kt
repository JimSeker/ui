package edu.cs4730.viewpagerdemo_kt

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.viewpagerdemo_kt.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"

    companion object {
        lateinit var leftfrag: FragLeft
        lateinit var midfrag: FragMid
        lateinit var rightfrag: FragRight
    }

    lateinit var binding: ActivityMainBinding
    lateinit var mViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left, systemBars.top, systemBars.right, systemBars.bottom
            )
            WindowInsetsCompat.CONSUMED
        }
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        leftfrag = FragLeft()
        midfrag = FragMid()
        rightfrag = FragRight()
        val fragmentManager = supportFragmentManager

        if (binding.pager != null) {
            //in portrait mode, so setup the viewpager with the fragments, using the adapter
            binding.pager!!.adapter = ThreeFragmentPagerAdapter(fragmentManager)
        } else {
            //in landscape mode  //so no viewpager.
            fragmentManager.beginTransaction().add(R.id.frag_left, leftfrag)
                .add(R.id.frag_mid, midfrag).add(R.id.frag_right, rightfrag).commit()
        }
    }

    /**
     * We need to extend a FragmentPagerAdapter to add our three fragments.
     *   We need to override getCount and getItem.  Also getPageTitle since we are
     *   using a PageStripe.
     */
    class ThreeFragmentPagerAdapter  //required constructor that simply supers.
        (fm: FragmentManager?) : FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        var PAGE_COUNT = 3

        // return the correct fragment based on where in pager we are.
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> leftfrag
                1 -> midfrag
                2 -> rightfrag
                else -> rightfrag  //won't let it be null, so use this one I guess.
            }
        }

        //how many total pages in the viewpager there are.  3 in this case.
        override fun getCount(): Int {
            return PAGE_COUNT
        }

        //getPageTitle required for the PageStripe to work and have a value.
        override fun getPageTitle(position: Int): CharSequence {

            //return String.valueOf(position);  //returns string of position for title
            return "Page " + (position + 1).toString()
        }
    }

}