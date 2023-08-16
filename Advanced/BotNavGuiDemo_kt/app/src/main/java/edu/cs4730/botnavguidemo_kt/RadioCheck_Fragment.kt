package edu.cs4730.botnavguidemo_kt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.botnavguidemo_kt.databinding.RadiocheckFragmentBinding

/**
 * Example of Radio button and check box.
 */
class RadioCheck_Fragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    var TAG = "RadioCheck_Fragment"
    lateinit var binding : RadiocheckFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RadiocheckFragmentBinding.inflate(inflater, container, false)

        //setup the radiogroup.
        //listener is onCheckedChanged and implemented from class level.
        binding.radioGroup1.setOnCheckedChangeListener(this)

        //setup the check box.
        binding.checkBox1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            //We know which checkbox, so ignoring buttonView variable.
            if (isChecked) {
                binding.checkBox1.setText("Uncheck me and I'll clear the raido Buttons too!")
            } else {
                binding.radioGroup1.clearCheck() //clears any radio buttons.
                binding.checkBox1.setText("Please Check me!")
            }
        })
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d(TAG, "onAttach")
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (group == binding.radioGroup1) { //if not, we are in trouble!
            if (checkedId == R.id.radio0) {
                // information radio button clicked
                Toast.makeText(requireContext(), "Radio Button 0 checked.", Toast.LENGTH_SHORT).show()
            } else if (checkedId == R.id.radio1) {
                // Confirmation radio button clicked
                Toast.makeText(requireContext(), "Radio Button 1 checked.", Toast.LENGTH_SHORT).show()
            } else if (checkedId == R.id.radio2) {
                //radio button 2 clicked
                Toast.makeText(requireContext(), "Radio Button 2 checked.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}