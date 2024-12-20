package edu.cs4730.modelviewrecyclerviewdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import edu.cs4730.modelviewrecyclerviewdemo_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    lateinit var mViewModel: DataViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        setSupportActionBar(binding.toolbar)
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
       binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Name is " + mViewModel.getItem(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mViewModel.itemLD.observe(this) { s ->
            Log.d(TAG, "triggered $s")
            Toast.makeText(baseContext, "MainActivity Received $s", Toast.LENGTH_LONG).show()
        }
    }
}
