package edu.cs4730.botnavguidemo_kt

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.cs4730.botnavguidemo_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    BottomNavigationDialogFragment.OnFragmentInteractionListener {

    var TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.bottomAppBar) //this set the bottom bar to the the Action Bar.
        binding.bottomAppBar.setTitle(R.string.title_home)
        fragmentManager = supportFragmentManager
        binding.bottomAppBar.setNavigationOnClickListener(View.OnClickListener {
            Log.d(TAG, "AppBar Navigation clicked.")
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                BottomNavigationDialogFragment()
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)

        })
        fragmentManager = supportFragmentManager
        binding.fab.setOnClickListener { // TODO
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
