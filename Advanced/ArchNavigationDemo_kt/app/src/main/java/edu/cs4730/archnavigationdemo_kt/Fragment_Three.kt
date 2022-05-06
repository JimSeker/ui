package edu.cs4730.archnavigationdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * This example receives arguments via the safe_arg version.  Note safe args are included in Project build.grade (not module).
 */
class Fragment_Three : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment__three, container, false)
        val tv_passed = myView.findViewById<TextView>(R.id.tv_passed)
        tv_passed.text = Fragment_ThreeArgs.fromBundle(requireArguments()).message
        val tv_passed2 = myView.findViewById<TextView>(R.id.tv_passed2)
        val stuff = "Data 2 is " + Fragment_ThreeArgs.fromBundle(requireArguments()).number
        tv_passed2.text = stuff
        return myView
    }
}