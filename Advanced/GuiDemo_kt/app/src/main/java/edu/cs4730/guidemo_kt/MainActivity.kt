package edu.cs4730.guidemo_kt

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import edu.cs4730.guidemo_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var mDrawerToggle: ActionBarDrawerToggle
    lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager

        //first setup the listview with some simple categories via an array.

        //first setup the listview with some simple categories via an array.
        val values = resources.getStringArray(R.array.sections)

        val adapter = ArrayAdapter(
            this,
            R.layout.drawer_list_item, values
        )
        binding.leftDrawer.adapter = adapter
        binding.leftDrawer.onItemClickListener =
            OnItemClickListener { arg0, view, position, index -> //based on click, change to correct fragment.
                val item = binding.leftDrawer.adapter.getItem(position).toString()
                when (position) {
                    0 -> fragmentManager.beginTransaction().replace(R.id.container, Text_Fragment())
                        .commit()
                    1 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, Input_Fragment()).commit()
                    2 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, Image_Fragment()).commit()
                    3 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, Button_Fragment()).commit()
                    4 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, ButtonCL_Fragment()).commit()
                    5 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, Relativelayout_Fragment()).commit()
                    6 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, RadioCheck_Fragment()).commit()
                    7 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, Spinner_Fragment()).commit()
                    8 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, ViewSwitch_Fragment()).commit()
                    9 -> fragmentManager.beginTransaction()
                        .replace(R.id.container, Picker_Fragment()).commit()
                }
                // update selected item and title, then close the drawer
                binding.leftDrawer.setItemChecked(position, true)
                //now close the drawer!
                binding.drawerLayout.closeDrawer(binding.leftDrawer)
            }

        // enable ActionBar app icon to behave as action to toggle nav drawer

        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  /* host Activity */
            binding.drawerLayout,  /* DrawerLayout object */
            R.string.drawer_open,  /* "open drawer" description for accessibility */
            R.string.drawer_close /* "close drawer" description for accessibility */
        ) {
            override fun onDrawerClosed(view: View) {
                supportActionBar!!.setTitle(R.string.app_name)
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                supportActionBar!!.title = "Categories"
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }
        binding.drawerLayout.addDrawerListener(mDrawerToggle)
        //first instance, so the default is zero.
        //first instance, so the default is zero.
        fragmentManager.beginTransaction().replace(R.id.container, Text_Fragment()).commit()
        binding.leftDrawer.setItemChecked(0, true)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    //needed to make the nav drawer draw correctly.
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