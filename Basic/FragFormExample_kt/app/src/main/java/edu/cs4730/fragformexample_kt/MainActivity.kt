package edu.cs4730.fragformexample_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        if (savedInstanceState == null) {
            myFormFragment = FormFragment()
            supportFragmentManager.beginTransaction().add(binding.container.id, myFormFragment)
                .commit()
        }
    }
}