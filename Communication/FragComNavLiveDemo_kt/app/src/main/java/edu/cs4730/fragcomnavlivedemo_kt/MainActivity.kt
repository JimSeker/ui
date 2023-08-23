package edu.cs4730.fragcomnavlivedemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.fragcomnavlivedemo_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: DataViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this)[DataViewModel::class.java]
    }
}