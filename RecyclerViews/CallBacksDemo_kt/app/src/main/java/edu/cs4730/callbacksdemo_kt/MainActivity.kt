package edu.cs4730.callbacksdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import edu.cs4730.callbacksdemo_kt.databinding.ActivityMainBinding

/*
 * This is a simple demo of how to get data from an recyclerview demo all the way back to the
 * mainactivity.
 *
 * Another interesting idea would be to just use a handler that is sent to
 * the adapter and then calls it (where it is declared here).  It's not implemented here, just
 * stated as another possibility.  See the viewmodel version for another way to do this.
 */
class MainActivity : AppCompatActivity(), MainFragment.OntransInteractionCallback {
    var TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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

    override fun ontransInteraction(item: String) {
        Log.v(TAG, "Callback at $TAG")
        Toast.makeText(this, "MainActivity Received $item", Toast.LENGTH_LONG).show()
    }
}
