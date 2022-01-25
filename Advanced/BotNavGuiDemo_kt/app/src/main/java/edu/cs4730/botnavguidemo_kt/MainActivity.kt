package edu.cs4730.botnavguidemo_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), BottomNavigationDialogFragment.OnFragmentInteractionListener {
    lateinit var appBar: BottomAppBar
    var TAG = "MainActivity"
    lateinit var  mDrawerLayout: DrawerLayout
    lateinit var mDrawerList: ListView
    lateinit var mDrawerToggle: ActionBarDrawerToggle
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBar = findViewById(R.id.bottomAppBar)
        setSupportActionBar(appBar) //this set the bottom bar to the the Action Bar.
        appBar.setTitle(R.string.title_home)
        fragmentManager = supportFragmentManager
        appBar.setNavigationOnClickListener(View.OnClickListener {
            Log.d(TAG, "AppBar Navigation clicked.")
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                BottomNavigationDialogFragment()
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
            //                return true;
        })
        fragmentManager = supportFragmentManager
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { // TODO
            Log.d(TAG, "Refresh FAB clicked.")
        }
        fragmentManager.beginTransaction().replace(R.id.container, Text_Fragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.navigation_home) {
            return true
        } else if (id == R.id.navigation_dashboard) {
            return true
        } else if (id == R.id.navigation_notifications) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentPicker(id: Int) {
        when (id) {
            R.id.m_text -> fragmentManager.beginTransaction()
                .replace(R.id.container, Text_Fragment()).commit()
            R.id.m_tex_input -> fragmentManager.beginTransaction()
                .replace(R.id.container, Input_Fragment()).commit()
            R.id.m_itemimages -> fragmentManager.beginTransaction()
                .replace(R.id.container, Image_Fragment()).commit()
            R.id.m_buttons -> fragmentManager.beginTransaction()
                .replace(R.id.container, Button_Fragment()).commit()
            R.id.m_itembuttons -> fragmentManager.beginTransaction()
                .replace(R.id.container, ButtonCL_Fragment()).commit()
            R.id.m_relative -> fragmentManager.beginTransaction()
                .replace(R.id.container, Relativelayout_Fragment()).commit()
            R.id.m_radio -> fragmentManager.beginTransaction()
                .replace(R.id.container, RadioCheck_Fragment()).commit()
            R.id.m_spinners -> fragmentManager.beginTransaction()
                .replace(R.id.container, Spinner_Fragment()).commit()
            R.id.m_itemviewswitcher -> fragmentManager.beginTransaction()
                .replace(R.id.container, ViewSwitch_Fragment()).commit()
            R.id.m_dialogpickers -> fragmentManager.beginTransaction()
                .replace(R.id.container, Picker_Fragment()).commit()
        }
    }
}
