package edu.cs4730.menudemo_kt

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import edu.cs4730.menudemo_kt.databinding.ActivityFragmenuBinding

/**
 * So this activity shows hwo the menu can change based on the fragments
 * which is FragMenu1 and FragMenu2 will show extra menu items.
 */
class FragMenuActivity : AppCompatActivity() {
    lateinit var one: FragMenu1
    lateinit var two: FragMenu2
    var isfrag1 = true
    lateinit var binding: ActivityFragmenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmenuBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        //turn on up button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //setup the two fragments, and then add one to screen.
        one = FragMenu1()
        two = FragMenu2()
        supportFragmentManager.beginTransaction().add(binding.textContainer.id, one).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_fragmenu, menu)
        if (isfrag1) {
            menu.findItem(R.id.frag1).isEnabled = false
            menu.findItem(R.id.frag2).isEnabled = true
        } else {
            menu.findItem(R.id.frag1).isEnabled = true
            menu.findItem(R.id.frag2).isEnabled = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            val upIntent = Intent(this, MainActivity::class.java)
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, upIntent)
            return true
        } else if (id == R.id.frag1) {
            if (!isfrag1) {
                supportFragmentManager.beginTransaction().replace(binding.textContainer.id, one)
                    .commit()
                isfrag1 = true
                supportInvalidateOptionsMenu()
            }
            return true
        } else if (id == R.id.frag2) {
            if (isfrag1) {
                supportFragmentManager.beginTransaction().replace(binding.textContainer.id, two)
                    .commit()
                isfrag1 = false
                supportInvalidateOptionsMenu()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}