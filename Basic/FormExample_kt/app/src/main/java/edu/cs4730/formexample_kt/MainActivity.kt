package edu.cs4730.formexample_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener, TextWatcher,
    View.OnClickListener {

    //variables for the widgets
    lateinit var myRadioGroup: RadioGroup
    lateinit var et: EditText
    lateinit var btnalert: Button
    lateinit var label: TextView

    //variable for the log
    var TAG = "ForExample"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText view setup and listener
        et = findViewById(R.id.ETname)
        et.addTextChangedListener(this)

        //the top label in the xml doc.
        label = findViewById(R.id.Label01)

        //setup the radio group with a listener.
        myRadioGroup = findViewById(R.id.SndGroup)
        myRadioGroup.setOnCheckedChangeListener(this)

        //setup the button with a listener as well.
        btnalert = findViewById(R.id.Button01)
        btnalert.setOnClickListener(this)
    }

    /*  Radio group listener for OnCheckedChangeListener */
    override fun onCheckedChanged(group: RadioGroup, CheckedId: Int) {
        if (group === myRadioGroup) { //if not myRadioGroup, we are in trouble!
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
        if (et.length() > 10) {
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
        if (v === btnalert) {
            if (et.text.toString().compareTo("") != 0) Toast.makeText(
                this,
                "The edittext has " + et.text.toString(),
                Toast.LENGTH_SHORT
            ).show() else Toast.makeText(this, "The button was pressed", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "The button was pressed.")
        }
    }
}