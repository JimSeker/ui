package edu.cs4730.fragformexample_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cs4730.fragformexample_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var myFormFragment: FormFragment
    private lateinit var binding: ActivityMainBinding

    //variable for the log
    var TAG = "FormFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.container) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            myFormFragment = FormFragment()
            supportFragmentManager.beginTransaction().add(binding.container.id, myFormFragment)
                .commit()
        }
    }
}