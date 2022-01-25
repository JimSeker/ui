package edu.cs4730.fragformexample_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var myFormFragment: FormFragment
    //variable for the log
    var TAG = "FormFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            myFormFragment = FormFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, myFormFragment).commit()
        }
    }
}