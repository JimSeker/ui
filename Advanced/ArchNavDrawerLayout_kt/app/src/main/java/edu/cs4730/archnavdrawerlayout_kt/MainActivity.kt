package edu.cs4730.archnavdrawerlayout_kt

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import edu.cs4730.archnavdrawerlayout_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar)

        //this is needed if you want the open/close to show in the toolbar.
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  // host activity
            binding.drawerLayout,  //drawerlayout object
            binding.appBar,  //toolbar
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
        binding.drawerLayout.addDrawerListener(mDrawerToggle)

        //Note for this to work with arch Navigation, these id must be the same id in menu.xml and the nav_graph.
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.action_first, R.id.action_second, R.id.action_third
        )
            .build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(binding.navview, navController)
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