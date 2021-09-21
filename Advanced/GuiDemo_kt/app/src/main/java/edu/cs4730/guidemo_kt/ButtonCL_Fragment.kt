package edu.cs4730.guidemo_kt

import android.content.Context
import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment


/**
 * This is basically code to show the constraintlayout with so many button.
 * the layout is very similar to the buttonfragment layout.
 */
class ButtonCL_Fragment : Fragment(), View.OnClickListener {
    var TAG = "Button_Fragment"
    lateinit var myContext: Context
    lateinit var output: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.constraintlayout_fragment, container, false)
        //set it up so all the button work in this fragment.
        myView.findViewById<View>(R.id.button01).setOnClickListener(this)
        myView.findViewById<View>(R.id.button02).setOnClickListener(this)
        myView.findViewById<View>(R.id.button03).setOnClickListener(this)
        myView.findViewById<View>(R.id.button04).setOnClickListener(this)
        myView.findViewById<View>(R.id.button05).setOnClickListener(this)
        myView.findViewById<View>(R.id.button06).setOnClickListener(this)
        myView.findViewById<View>(R.id.button07).setOnClickListener(this)
        myView.findViewById<View>(R.id.button08).setOnClickListener(this)
        myView.findViewById<View>(R.id.button09).setOnClickListener(this)
        myView.findViewById<View>(R.id.button10).setOnClickListener(this)
        myView.findViewById<View>(R.id.button11).setOnClickListener(this)
        //output to the screen.
        output = myView.findViewById(R.id.output)
        return myView
    }

    /*
     * This on is the for the implements View.OnClickListener
     *
     */
    override fun onClick(v: View) {
        Toast.makeText(myContext, "a button was clicked", Toast.LENGTH_SHORT).show()
        output!!.append("\na button was clicked")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }
}