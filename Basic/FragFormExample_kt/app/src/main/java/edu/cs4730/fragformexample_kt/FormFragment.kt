package edu.cs4730.fragformexample_kt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


/**
 * The meat of the example is here, instead of the mainActivity.  OnCreateView has the setup
 * and then all the listeners.
 */

class FormFragment : Fragment(), RadioGroup.OnCheckedChangeListener,
    TextWatcher, View.OnClickListener {
    //variables for the widgets
    lateinit var myRadioGroup: RadioGroup
    lateinit var et: EditText
    lateinit var btnalert: Button
    lateinit var label: TextView

    //variable for the log
    var TAG = "FormFragment"

    //OnCreateView is where everything is inflated and any listeners are setup at.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_form, container, false)

        //EditText view setup and listner
        et = myView.findViewById(R.id.ETname)
        et.addTextChangedListener(this)

        //the top label in the xml doc.
        label = myView.findViewById(R.id.Label01)

        //setup the radio group with a listener.
        myRadioGroup = myView.findViewById(R.id.SndGroup)
        myRadioGroup.setOnCheckedChangeListener(this)

        //setup the button with a listener as well.
        btnalert = myView.findViewById(R.id.Button01)
        btnalert.setOnClickListener(this)
        return myView
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
                Toast.makeText(activity, "Warning!", Toast.LENGTH_LONG).show()
            }
        }
    }

    /* EditView listeners */
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (et.length() > 10) {
            Toast.makeText(activity, "Long Word!", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Long Word!")
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //left blank
    }

    override fun afterTextChanged(s: Editable?) {
        //left blank
    }

    /* button listener */
    override fun onClick(v: View) {
        if (v === btnalert) {
            Toast.makeText(activity, "The button was pressed", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "The button was pressed.")
        }
    }
}
