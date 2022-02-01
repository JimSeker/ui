package edu.cs4730.callbacksitemviewdemo_kt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * This is a simple demo of how to get data from an recyclerview demo all the way back to the
 * mainactivity without using any buttons.  The user can click on the item in the recyclerview and
 * it comes all the way back to mainactivity with info.
 *
 * Note it's itemview in the adapter that is clickable.  this is the callback.
 *
 */
class MainActivity : AppCompatActivity(), MainFragment.OntransInteractionCallback {
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun ontransInteraction(item: String) {
        Log.v(TAG, "Callback at $TAG")
        Toast.makeText(this, "MainActivity Received $item", Toast.LENGTH_LONG).show()
    }
}
