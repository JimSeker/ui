package edu.cs4730.menudemo_kt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import edu.cs4730.menudemo_kt.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.popup.setOnClickListener { v ->
            showPopupMenu(v) //this is to the code below, not an API call.
        }
    }

    private fun showPopupMenu(v: View) {
        val popupM = PopupMenu(
            this,
            v
        ) //note "this" is the activity context, if you are using this in a fragment.  using getActivity()
        popupM.inflate(R.menu.popup)
        popupM.setOnMenuItemClickListener { item ->
            Toast.makeText(this@MainActivity, item.toString(), Toast.LENGTH_LONG).show()
            true
        }
        popupM.show()
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
        if (id == R.id.menuandfragdemo) {
            startActivity(Intent(this@MainActivity, FragMenuActivity::class.java))
            return true
        } else if (id == R.id.actbaritemsdemo) {
            startActivity(Intent(this@MainActivity, ActionMenuActivity::class.java))
            return true
        } else if (id == R.id.viewpagerbuttondemo) {
            startActivity(Intent(this@MainActivity, ViewPagerButtonMenuActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
