package edu.cs4730.supportdesignbottomnavdemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView
import edu.cs4730.supportdesignbottomnavdemo_kt.databinding.ActivityMainBinding


/**
 * This is a simple example of how to use a the BottomNavigationView, which was
 * introduced in support design v25.0.0.   The navigation changes the fragment, using the
 * onItemSelected listener.  Note the list for the navigation view is a menu xml list, found
 * in the menu resource.
 */
class MainActivity : AppCompatActivity() {
    lateinit var recentFrag: BlankFragment
    lateinit var favFrag: BlankFragment
    lateinit var nearbyFrag: BlankFragment
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.activityMain) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //setup the three fragments, which is just the same blankfragment with a different text, that will be used by the nav.
        recentFrag = BlankFragment.newInstance("Recent", "")
        favFrag = BlankFragment.newInstance("Favorites", "")
        nearbyFrag = BlankFragment.newInstance("Nearby", "")

        binding.bnv.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item -> //At this point, we are doing the same thing that is done for menu selections.
            //if we had a onOptionsItemSelect method for a menu, we could just use it.
            val id = item.itemId
            if (id == R.id.recent) {
                supportFragmentManager.beginTransaction().replace(binding.container.id, recentFrag)
                    .commit()
                return@OnItemSelectedListener true
            } else if (id == R.id.favorites) {
                supportFragmentManager.beginTransaction().replace(binding.container.id, favFrag)
                    .commit()
                return@OnItemSelectedListener true
            } else if (id == R.id.nearby) {
                supportFragmentManager.beginTransaction().replace(binding.container.id, nearbyFrag)
                    .commit()
                return@OnItemSelectedListener true
            }
            false
        })

        //set the first one as the default.
        supportFragmentManager.beginTransaction().add(binding.container.id, recentFrag).commit()
    }
}
