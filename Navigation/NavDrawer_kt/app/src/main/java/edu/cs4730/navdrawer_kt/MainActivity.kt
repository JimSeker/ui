package edu.cs4730.navdrawer_kt

import android.app.SearchManager
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import edu.cs4730.navdrawer_kt.databinding.ActivityMainBinding
import java.util.*


/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.  This is google's example from their web site
 * http://developer.android.com/training/implementing-navigation/nav-drawer.html with modifications for lollipop ish code.
 * now converted to kotlin.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawerTitle: CharSequence
    private lateinit var mTitle: CharSequence
    private lateinit var mPlanetTitles: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDrawerTitle = title
        mTitle = mDrawerTitle
        mPlanetTitles = resources.getStringArray(R.array.planets_array)

        // set a custom shadow that overlays the main content when the drawer opens
        binding.drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START)
        // set up the drawer's list view with items and click listener
        binding.leftDrawer.adapter = ArrayAdapter(this, R.layout.drawer_list_item, mPlanetTitles)
        binding.leftDrawer.onItemClickListener = DrawerItemClickListener()

        // enable ActionBar app icon to behave as action to toggle nav drawer
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = object : ActionBarDrawerToggle(
            this,  /* host Activity */
            binding.drawerLayout,  /* DrawerLayout object */
            R.string.drawer_open,  /* "open drawer" description for accessibility */
            R.string.drawer_close /* "close drawer" description for accessibility */
        ) {
            override fun onDrawerClosed(view: View) {
                supportActionBar!!.title = mTitle
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                supportActionBar!!.title = mDrawerTitle
                invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }
        binding.drawerLayout.addDrawerListener(mDrawerToggle)
        if (savedInstanceState == null) {
            selectItem(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /* Called whenever we call invalidateOptionsMenu() */
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // If the nav drawer is open, hide action items related to the content view
        val drawerOpen = binding.drawerLayout.isDrawerOpen(binding.leftDrawer)
        menu.findItem(R.id.action_websearch).isVisible = !drawerOpen
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        // Handle action buttons
        if (item.itemId == R.id.action_websearch) { // create intent to perform web search for this planet
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, supportActionBar!!.title)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /* The click listener for ListView in the navigation drawer */
    private inner class DrawerItemClickListener : OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            selectItem(position)
        }
    }

    private fun selectItem(position: Int) {
        // update the main content by replacing fragments
        val fragment: Fragment = PlanetFragment()
        val args = Bundle()
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position)
        fragment.arguments = args
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()

        // update selected item and title, then close the drawer
        binding.leftDrawer.setItemChecked(position, true)
        title = mPlanetTitles[position]
        binding.drawerLayout.closeDrawer(binding.leftDrawer)
    }

    override fun setTitle(title: CharSequence) {
        mTitle = title
        supportActionBar!!.title = mTitle
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

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    class PlanetFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_planet, container, false)
            val i = requireArguments().getInt(ARG_PLANET_NUMBER)
            val planet = resources.getStringArray(R.array.planets_array)[i]
            val imageId = resources.getIdentifier(
                planet.lowercase(Locale.getDefault()), "drawable", requireActivity().packageName
            )
            (rootView.findViewById<View>(R.id.image) as ImageView).setImageResource(imageId)
            requireActivity().title = planet
            return rootView
        }

        companion object {
            const val ARG_PLANET_NUMBER = "planet_number"
        }
    }
}