package edu.cs4730.fragcomnavvmodeldemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.fragcomnavvmodeldemo_kt.databinding.ActivityMainBinding

/**
 * simple example use a AndroidviewModel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 *
 * the numbers are not live data, so no observers.  those are set "manually".  while the string data item is using mutable live data
 * so an observer can be used to update the data whenever it changes.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: DataViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        mViewModel.data.observe(this, Observer<String?> { data -> //do something with the data
            Log.d("MainActivity", data!!)
        })

    }
}