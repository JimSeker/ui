package edu.cs4730.menudemo_kt

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import androidx.core.app.NavUtils
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View

/**
 * This activity show how to setup both buttons, and menus (and sub menus).
 * but all the work is in the menu/action.xml file.
 */
class ActionMenuActivity : AppCompatActivity() {
    lateinit var label1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actionmenu)
        label1 = findViewById<View>(R.id.label1) as TextView

        //turn on up button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // Navigate "up" the demo structure to the launchpad activity.
            // See http://developer.android.com/design/patterns/navigation.html for more.
            NavUtils.navigateUpTo(this, Intent(this, MainActivity::class.java))
            return true
        }
        //Normally you would deal with all the menu items here, which I'm ignoring for the purpose of the example.
        return super.onOptionsItemSelected(item)
    }
}