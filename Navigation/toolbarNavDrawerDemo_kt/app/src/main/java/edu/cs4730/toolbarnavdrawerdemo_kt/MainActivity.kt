package edu.cs4730.toolbarnavdrawerdemo_kt

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
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawerlayout: DrawerLayout
    private lateinit var mDrawerList: ListView
    private lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //use the androidx... .toolbar instead of the actionbar, which is disabled in the style.
        toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)

        //now the rest of the code matches NavDrawerDemo.
        mTextView = findViewById(R.id.textview01)
        mDrawerlayout = findViewById(R.id.drawer_layout)

        //lastly setup the listview with some simple categories via an array.
        val values = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        val adapter = ArrayAdapter(
            this,
            R.layout.drawer_list_item, values
        )
        mDrawerList = findViewById(R.id.left_drawer)
        mDrawerList.adapter = adapter
        mDrawerList.onItemClickListener =
            OnItemClickListener { arg0, view, position, index -> //yes, this should do something for more interesting.  but this is an example.
                val item = mDrawerList.adapter.getItem(position).toString()
                mTextView.text = item
                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true)
                //now close the drawer!
                mDrawerlayout.closeDrawers()
            }

        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
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
                supportActionBar!!.setTitle("Categories")
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
        mDrawerlayout.addDrawerListener(mDrawerToggle)
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
