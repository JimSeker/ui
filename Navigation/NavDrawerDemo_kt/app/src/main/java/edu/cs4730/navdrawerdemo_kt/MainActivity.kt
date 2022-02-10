package edu.cs4730.navdrawerdemo_kt

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout


class MainActivity : AppCompatActivity() {
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mDrawerList: ListView
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView = findViewById(R.id.textview01)
        mDrawerLayout = findViewById(R.id.drawer_layout)

        //first setup the listview with some simple categories via an array.
        val values = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(
            this,
            R.layout.drawer_list_item, values
        )
        mDrawerList = findViewById<View>(R.id.left_drawer) as ListView
        mDrawerList.adapter = adapter
        mDrawerList.onItemClickListener =
            OnItemClickListener { arg0, view, position, index -> //yes, this should do something for more interesting.  but this is an example.
                val item = mDrawerList.adapter.getItem(position).toString()
                mTextView.text = item
                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true)
                //now close the drawer!
                mDrawerLayout.closeDrawer(mDrawerList)
            }
        //set the default value and position to checked.
        mDrawerList.setItemChecked(0, true)
        mTextView.text = values[0]

        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)


        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  /* host Activity */
            mDrawerLayout,  /* DrawerLayout object */
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
        mDrawerLayout.addDrawerListener(mDrawerToggle)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
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
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig)
    }
}
