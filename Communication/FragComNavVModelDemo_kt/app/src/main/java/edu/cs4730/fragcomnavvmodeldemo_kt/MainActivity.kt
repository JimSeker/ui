package edu.cs4730.fragcomnavvmodeldemo_kt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

/**
 * simple example use a AndroidviewModel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 *
 * the numbers are not live data, so no observers.  those are set "manually".  while the string data item is using mutable live data
 * so an observer can be used to update the data whenever it changes.
 */

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        mViewModel.data.observe(this, Observer<String?> { data -> //do something with the data
            Log.d("MainActivity", data!!)
        })

    }
}