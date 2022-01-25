package edu.cs4730.botnavguidemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Example of Radio button
 * and check box.
 */
class RadioCheck_Fragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    var TAG = "RadioCheck_Fragment"
    lateinit var myContext: Context //needed for a number of things, but mostly for toast, set in onAttach.
    lateinit var myRadioGroup: RadioGroup
    lateinit var myCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.radiocheck_fragment, container, false)

        //setup the radiogroup.
        myRadioGroup = view.findViewById(R.id.radioGroup1) //get the radio group
        //note, we didn't get the individual radio Buttons, we don't normally need them.
        //listener is onCheckedChanged and implemented from class level.
        myRadioGroup.setOnCheckedChangeListener(this)

        //setup the check box.
        myCheckBox = view.findViewById(R.id.checkBox1)
        myCheckBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            //We know which checkbox, so ignoring buttonView variable.
            if (isChecked) {
                myCheckBox.setText("Uncheck me and I'll clear the raido Buttons too!")
            } else {
                myRadioGroup.clearCheck() //clears any radio buttons.
                myCheckBox.setText("Please Check me!")
            }
        })
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (group === myRadioGroup) { //if not, we are in trouble!
            if (checkedId == R.id.radio0) {
                // information radio button clicked
                Toast.makeText(myContext, "Radio Button 0 checked.", Toast.LENGTH_SHORT).show()
            } else if (checkedId == R.id.radio1) {
                // Confirmation radio button clicked
                Toast.makeText(myContext, "Radio Button 1 checked.", Toast.LENGTH_SHORT).show()
            } else if (checkedId == R.id.radio2) {
                //radio button 2 clicked
                Toast.makeText(myContext, "Radio Button 2 checked.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}