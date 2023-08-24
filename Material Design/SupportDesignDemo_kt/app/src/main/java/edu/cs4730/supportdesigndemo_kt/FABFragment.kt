package edu.cs4730.supportdesigndemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.supportdesigndemo_kt.databinding.FragmentFabBinding

/**
 * A simple demo of the Foating Action button.
 * This code does very little and looks just like you would use a button actualy.
 * see the xml doc.
 */
class FABFragment : Fragment() {
    lateinit var binding: FragmentFabBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFabBinding.inflate(inflater, container, false)
        binding.fab1.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "You clicked add!",
                Toast.LENGTH_LONG
            ).show()
        }
        binding.fab2.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "You clicked check!",
                Toast.LENGTH_LONG
            ).show()
        }
        return binding.root
    }
}