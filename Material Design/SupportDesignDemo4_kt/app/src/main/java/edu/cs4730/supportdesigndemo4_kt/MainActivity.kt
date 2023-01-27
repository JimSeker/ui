package edu.cs4730.supportdesigndemo4_kt

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.navigation.NavigationView
import java.util.*


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
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: myAdapter
    private lateinit var toolbar: Toolbar
    lateinit var mCollapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawerlayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //use the toolbar instead of the default one.
        toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)


        //SO to get any text or title it is set here.  otherwise, there is no text in the toolbar.
        mCollapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout1)
        mCollapsingToolbarLayout.title = resources.getString(R.string.app_name)


        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        val values = Arrays.asList(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )

        //setup the RecyclerView
        mRecyclerView = findViewById(R.id.list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = myAdapter(values, R.layout.my_row, this)
        //add the adapter to the recyclerview
        mRecyclerView.adapter = mAdapter

        //standard navigation drawer setup.
        mDrawerlayout = findViewById(R.id.drawer_layout)
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  // host activity
            mDrawerlayout,  //drawerlayout object
            toolbar,  //toolbar
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
                mCollapsingToolbarLayout.title = resources.getString(R.string.app_name)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }
        //To disable the icon for the drawer, change this to false
        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerlayout.addDrawerListener(mDrawerToggle)
        //this ia the support Navigation view.
        mNavigationView = findViewById(R.id.navview)
        //setup a listener, which acts very similiar to how menus are handled.
        mNavigationView.setNavigationItemSelectedListener { menuItem ->
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
            mDrawerlayout.closeDrawers()
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
