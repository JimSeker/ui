package edu.cs4730.archnavigationdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * This is a simple fragment to show use to receive a bundle via the nav_graph.  see fragment_two for the
 * bundle creation and send.
 */
class Fragment_bundle : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_bundle, container, false)
        val tv_passed = myView.findViewById<TextView>(R.id.btv_passed)
        val tv_passed2 = myView.findViewById<TextView>(R.id.btv_passed2)

        //via a bundle, instead of safe args
        tv_passed.text = requireArguments().getString("message", "no data")
        val stuff = "Data 2 is " + requireArguments().getInt("number", 1)
        tv_passed2.text = stuff
        return myView
    }
}