package edu.cs4730.fragdemosimple_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cs4730.fragdemosimple_kt.databinding.ActivityMainBinding

/**
 * This is a very simple demo of changing between two fragments.
 * This really not a good way to do this and is for demo purposes.  I needed something
 * really simple to start out with.
 */

class MainActivity : AppCompatActivity() {

    var firstfragment = true
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //if this a not new, then place add firstfragment to the framelayout
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.container.id, oneFragment())
                .commit()
        }
        //find the button and setup the listener.
        binding.button01.setOnClickListener {
            firstfragment = if (firstfragment) {
                //first fragment is showing, so replace it with the second one.
                supportFragmentManager.beginTransaction()
                    .replace(binding.container.id, twoFragment())
                    .commit()
                false
            } else {
                //second fragment is showing, so replace it with the second one.
                supportFragmentManager.beginTransaction()
                    .replace(binding.container.id, oneFragment())
                    .commit()
                true
            }
        }

    }
}