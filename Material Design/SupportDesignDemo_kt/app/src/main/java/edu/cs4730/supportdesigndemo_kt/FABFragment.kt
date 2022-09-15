package edu.cs4730.supportdesigndemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * A simple demo of the Foating Action button.
 * This code does very little and looks just like you would use a button actualy.
 * see the xml doc.
 */
class FABFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_fab, container, false)
        myView.findViewById<View>(R.id.fab1).setOnClickListener {
            Toast.makeText(
                requireContext(),
                "You clicked add!",
                Toast.LENGTH_LONG
            ).show()
        }
        myView.findViewById<View>(R.id.fab2).setOnClickListener {
            Toast.makeText(
                requireContext(),
                "You clicked check!",
                Toast.LENGTH_LONG
            ).show()
        }
        return myView
    }
}