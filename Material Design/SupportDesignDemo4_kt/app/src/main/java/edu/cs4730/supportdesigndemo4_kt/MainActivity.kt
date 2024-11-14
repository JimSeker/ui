package edu.cs4730.supportdesigndemo4_kt

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import edu.cs4730.supportdesigndemo4_kt.databinding.ActivityMainBinding

/**
 * an attempt at a collapsing toolbar (will, I thought it would scroll off as well).  show title text
 * is an issue.
 *
 *
 * based on http://android-developers.blogspot.com/2015/05/android-design-support-library.html  so
 * based on android docs in the past... it's an error in their code... or as it turns out a note, that
 * well isn't clear at all.
 *
 *
 * basing come from the talltoolbar example to get icons and stuff.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: myAdapter
    private lateinit var mDrawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //use the toolbar instead of the default one.
        setSupportActionBar(binding.appBar)

        //SO to get any text or title it is set here.  otherwise, there is no text in the toolbar.
        binding.collapsingtoolbarlayout1.title = resources.getString(R.string.app_name)


        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        val values = listOf("Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2")

        //setup the RecyclerView
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = myAdapter(values, this)
        //add the adapter to the recyclerview
        binding.list.adapter = mAdapter

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
                //likely don't need this.
                supportActionBar!!.setTitle(R.string.app_name)
                //need this or no title shows.
                binding.collapsingtoolbarlayout1.title = resources.getString(R.string.app_name)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }
        //To disable the icon for the drawer, change this to false
        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        binding.drawerLayout.addDrawerListener(mDrawerToggle)
        //this ia the support Navigation view.
        //setup a listener, which acts very similiar to how menus are handled.
        binding.navview.setNavigationItemSelectedListener { menuItem ->
            //instead of this below, we could just call the
            //onOptionsItemSelected(menuItem);
            val id = menuItem.itemId
            //noinspection SimplifiableIfStatement
            if (id == R.id.navigation_item_1) {
                //do something.
            } else if (id == R.id.navigation_item_2) {
                //do something
            } else if (id == R.id.navigation_item_3) {
                //do something.
            } else if (id == R.id.navigation_item_4) {
                //do something.
            }
            menuItem.isChecked = true
            binding.drawerLayout.closeDrawers()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
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
