package edu.cs4730.archnavdrawerlayout_kt

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawerlayout: DrawerLayout
    private lateinit var mNavigationView: NavigationView
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)

        //standard navigation drawer setup.
        mDrawerlayout = findViewById(R.id.drawer_layout)

        //this is needed if you want the open/close to show in the toolbar.
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  // host activity
            mDrawerlayout,  //drawerlayout object
            toolbar,  //toolbar
            R.string.drawer_open,  //open drawer description  required!
            R.string.drawer_close //closed drawer description
        ) {

            //called once the drawer has closed.
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                //supportActionBar?.title = "Pick one"
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            //called when the drawer is now open.
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                //supportActionBar?.title = R.string.app_name.toString()
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }
        mDrawerlayout.addDrawerListener(mDrawerToggle)

        //this ia the support Navigation view.
        mNavigationView = findViewById(R.id.navview)
        //setup a listener, which acts very similar to how menus are handled, but with a NavView and arch Navigation
        //this how to do it manually.
//        mNavigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem -> //we could just as easily call onOptionsItemSelected(menuItem) and how it deal with it.
//            val id = menuItem.itemId
//            if (id == R.id.action_first) {
//                //load fragment
//                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
//                    menuItem.isChecked = true //make sure to check/highlight the item.
//                    supportFragmentManager.beginTransaction().replace(R.id.container, OneFragment())
//                        .commit()
//                }
//                mDrawerlayout.closeDrawers() //close the drawer, since the user has selected it.
//                return@OnNavigationItemSelectedListener true
//            } else if (id == R.id.action_second) {
//                //load fragment
//                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
//                    menuItem.isChecked = true //make sure the item is checked/highlighted
//                    supportFragmentManager.beginTransaction().replace(R.id.container, TwoFragment())
//                        .commit()
//                }
//                //now close the nav drawer.
//                mDrawerlayout.closeDrawers()
//                return@OnNavigationItemSelectedListener true
//            } else if (id == R.id.action_third) {
//                //load fragment
//                if (!menuItem.isChecked) {  //only need to do this if fragment is already loaded.
//                    menuItem.isChecked = true
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.container, threeFragment()).commit()
//                }
//                mDrawerlayout.closeDrawers()
//                return@OnNavigationItemSelectedListener true
//            }
//            false
//        })

        //Note for this to work with arch Navigation, these id must be the same id in menu.xml and the nav_graph.
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.action_first, R.id.action_second, R.id.action_third
        )
            .build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(mNavigationView, navController)
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