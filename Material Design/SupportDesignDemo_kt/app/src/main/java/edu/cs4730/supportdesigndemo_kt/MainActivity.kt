package edu.cs4730.supportdesigndemo_kt

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import edu.cs4730.supportdesigndemo_kt.databinding.ActivityMainBinding

/*
 * This example is to show many of the features available in the support design library.
 * http://android-developers.blogspot.com/2015/05/android-design-support-library.html
 *
 * Many of them are in the fragments.
 *
 * But one feature is the show here (and xml) is the NavigationView in the Navigation Draw.
 * It allows the user to create a layout similar to google's play nav drawer.  With a layout
 * at the top and a set of items via a menu xml file.
 *
 *
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    lateinit var fragmentManager: FragmentManager
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //use the v7.toolbar instead of the default one.
        setSupportActionBar(binding.appBar)

        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        //standard navigation drawer setup.
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  // host activity
            binding.drawerLayout,  //drawerlayout object
            binding.appBar,  //toolbar
            R.string.drawer_open,  //open drawer description  required!
            R.string.drawer_close
        ) {
            //closed drawer description
            //called once the drawer has closed.
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                supportActionBar!!.title = "Categories"
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            //called when the drawer is now open.
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                supportActionBar!!.setTitle(R.string.app_name)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }
        //To disable the icon for the drawer, change this to false
        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        binding.drawerLayout.addDrawerListener(mDrawerToggle)

        //this ia the support Navigation view.
        //setup a listener, which acts very similar to how menus are handled.
        binding.navview.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem -> //we could just as easily call onOptionsItemSelected(menuItem) and how it deal with it.
            val id = menuItem.itemId
            if (id == R.id.navigation_item_1) {
                //load fragment
                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
                    menuItem.isChecked = true //make sure to check/highlight the item.
                    fragmentManager.beginTransaction()
                        .replace(binding.container.id, SnackBarFragment()).commit()
                }
                binding.drawerLayout.closeDrawers() //close the drawer, since the user has selected it.
                return@OnNavigationItemSelectedListener true

            } else if (id == R.id.navigation_item_2) {
                //load fragment
                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
                    menuItem.isChecked = true //make sure the item is checked/highlighted
                    Log.v(TAG, "fab fragment?")
                    fragmentManager.beginTransaction().replace(binding.container.id, FABFragment())
                        .commit()
                }
                //now close the nav drawer.
                binding.drawerLayout.closeDrawers()
                return@OnNavigationItemSelectedListener true
            } else if (id == R.id.navigation_item_3) {
                //load fragment
                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
                    menuItem.isChecked = true
                    fragmentManager.beginTransaction()
                        .replace(binding.container.id, SB_FABFragment()).commit()
                }
                binding.drawerLayout.closeDrawers()
                return@OnNavigationItemSelectedListener true
            } else if (id == R.id.navigation_item_4) {
                //load fragment
                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
                    menuItem.isChecked = true
                    fragmentManager.beginTransaction()
                        .replace(binding.container.id, TextInputLayoutFragment()).commit()
                }
                binding.drawerLayout.closeDrawers()
                return@OnNavigationItemSelectedListener true
            }
            false
        })

        //finally deal with the fragments.
        fragmentManager = supportFragmentManager
        //first instance, so the default is zero.
        fragmentManager.beginTransaction().replace(binding.container.id, SnackBarFragment())
            .commit()
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig)
    }
}
