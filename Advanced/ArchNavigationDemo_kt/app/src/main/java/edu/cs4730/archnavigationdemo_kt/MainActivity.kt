package edu.cs4730.archnavigationdemo_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.cs4730.archnavigationdemo_kt.databinding.ActivityMainBinding

/**
 * Nothing really to see here.  It's all about the fragments and Navigation demo.
 * This is handled by the layout and res/navigation.
 */

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}