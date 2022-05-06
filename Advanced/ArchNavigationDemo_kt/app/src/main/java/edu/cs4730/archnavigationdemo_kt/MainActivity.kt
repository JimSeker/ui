package edu.cs4730.archnavigationdemo_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Nothing really to see here.  It's all about the fragments and Navigation demo.
 * This is handled by the layout and res/navigation.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}