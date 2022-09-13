package edu.cs4730.simplefragcomdemo_kt

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

/**
 * a simple fragment to display data.
 */
class InfoFragment : Fragment() {
    var label: TextView? = null
    var num = 0
    fun update(i: Int) {
        num = num + i
        if (label != null)
            label!!.text = "Number of clicks: $num"
        else
            Log.v("Info", "label is null")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_info, container, false)
        label = myView.findViewById(R.id.numclick)
        label!!.text = "Number of clicks: $num"
        return myView
    }
}