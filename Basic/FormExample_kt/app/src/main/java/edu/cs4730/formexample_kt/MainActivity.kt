package edu.cs4730.formexample_kt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import edu.cs4730.formexample_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener, TextWatcher,
    View.OnClickListener {

    //variables for the widgets
//    lateinit var myRadioGroup: RadioGroup
//    lateinit var et: EditText
//    lateinit var btnalert: Button
//    lateinit var label: TextView

    //variable for the log
    var TAG = "ForExample"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //EditText view setup and listener
        //et = findViewById(R.id.ETname)
        binding.ETname.addTextChangedListener(this)

        //the top label in the xml doc.
        //label = findViewById(R.id.Label01)

        //setup the radio group with a listener.
        //myRadioGroup = findViewById(R.id.myRadioGroup)
        binding.myRadioGroup.setOnCheckedChangeListener(this)

        //setup the button with a listener as well.
        //btnalert = findViewById(R.id.btnalert)
        binding.btnalert.setOnClickListener(this)
    }

    /*  Radio group listener for OnCheckedChangeListener */
    override fun onCheckedChanged(group: RadioGroup, CheckedId: Int) {
        if (group === binding.myRadioGroup) { //if not myRadioGroup, we are in trouble!
            if (CheckedId == R.id.RB01) {
                // information radio button clicked
                Log.d(TAG, "RB01 was pushed.")
            } else if (CheckedId == R.id.RB02) {
                // Confirmation radio button clicked
                Log.d(TAG, "RB02 was pushed.")
            } else if (CheckedId == R.id.RB03) {
                // Warning radio button clicked
                Toast.makeText(this, "Warning!", Toast.LENGTH_LONG).show()
            }
        }
    }

    /* EditView listeners */
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (binding.ETname.length() > 10) {
            Toast.makeText(this, "Long Word!", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Long Word!")
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        //left blank
    }

    override fun afterTextChanged(s: Editable) {
        //left blank
    }

    /* button listener */
    override fun onClick(v: View) {
        if (v === binding.btnalert) {
            if (binding.ETname.text.toString().compareTo("") != 0) Toast.makeText(
                this,
                "The edittext has " + binding.ETname.text.toString(),
                Toast.LENGTH_SHORT
            ).show() else Toast.makeText(this, "The button was pressed", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "The button was pressed.")
        }
    }
}