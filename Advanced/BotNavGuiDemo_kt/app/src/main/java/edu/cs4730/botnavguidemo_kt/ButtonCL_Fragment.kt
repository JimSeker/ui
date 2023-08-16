package edu.cs4730.botnavguidemo_kt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.botnavguidemo_kt.databinding.ConstraintlayoutFragmentBinding


/**
 * This is basically code to show the constraintlayout with so many button.
 * the layout is very similar to the buttonfragment layout.
 */
class ButtonCL_Fragment : Fragment(), View.OnClickListener {
    var TAG = "Button_Fragment"
    lateinit var binding: ConstraintlayoutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ConstraintlayoutFragmentBinding.inflate(inflater, container, false)
        //set it up so all the button work in this fragment.
        binding.button01.setOnClickListener(this)
        binding.button02.setOnClickListener(this)
        binding.button03.setOnClickListener(this)
        binding.button04.setOnClickListener(this)
        binding.button05.setOnClickListener(this)
        binding.button06.setOnClickListener(this)
        binding.button07.setOnClickListener(this)
        binding.button08.setOnClickListener(this)
        binding.button09.setOnClickListener(this)
        binding.button10.setOnClickListener(this)
        binding.button11.setOnClickListener(this)
        return binding.root
    }

    /**
     * This on is the for the implements View.OnClickListener
     */
    override fun onClick(v: View) {
        Toast.makeText(requireContext(), "a button was clicked", Toast.LENGTH_SHORT).show()
        binding.output.append("\na button was clicked")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }
}