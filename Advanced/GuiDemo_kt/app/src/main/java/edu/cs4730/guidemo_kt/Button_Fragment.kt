package edu.cs4730.guidemo_kt

import android.content.Context
import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import edu.cs4730.guidemo_kt.databinding.ButtonFragmentBinding

class Button_Fragment : Fragment(), View.OnClickListener {
    var TAG = "Button_Fragment"
    lateinit var binding: ButtonFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        binding = ButtonFragmentBinding.inflate(inflater, container, false)

        binding.button01.setOnClickListener { binding.output.text = "Output:\n Button1" }

        binding.button02.setOnClickListener(this)
        binding.button03.setOnClickListener(this)
        //binding makes it very simple to setup listeners, unless with findviewbyid there is 3 ways.
        //note setting the listener in the xml android:onclick= will call the MainActivity, not the fragment!

        //binding makes it very simple to setup listeners, unless with findviewbyid there is 3 ways.
        //note setting the listener in the xml android:onclick= will call the MainActivity, not the fragment!
        return binding.root
    }

    /**
     * This on is the for the implements View.OnClickListener
     */
    override fun onClick(v: View) {
        if (v == binding.button02) {  //it's button 2
            Toast.makeText(requireContext(), "Button 2 was clicked", Toast.LENGTH_SHORT).show()
        } else if (v == binding.button03) {  //it's button 3
            binding.output.text = "Output:\nButton3"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }
}