package edu.cs4730.guidemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment

/**
 * This shows how the view switcher can work.
 * It's in interesting one, because we can change a "static" layout pretty easy.
 */
class ViewSwitch_Fragment : Fragment() {
    var TAG = "ViewSwitch_fragment"
    lateinit var myContext: Context

    //Switcher to change view when input mode changes
    lateinit var myViewSwitch: ViewSwitcher
    lateinit var myButton: Button

    //ViewFlipper variables.
    lateinit var myViewFlipper: ViewFlipper
    lateinit var myVFButton: Button
    lateinit var myVFanim: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.viewswitch_fragment, container, false)

        //setup the ViewSwitch variable.
        myViewSwitch = myView.findViewById(R.id.viewSwitcher1)

        //setup the button
        myButton = myView.findViewById(R.id.vs_button)
        myButton.setOnClickListener(View.OnClickListener { myViewSwitch.showNext() })

        //ViewFlipper code
        myViewFlipper = myView.findViewById(R.id.viewFlipper1)
        //set animation, which can be used in viewswitcher as well
        myViewFlipper.setInAnimation(
            AnimationUtils.loadAnimation(
                myContext,
                android.R.anim.slide_in_left
            )
        ) //or android.R.anim.fade_in
        myViewFlipper.setOutAnimation(
            AnimationUtils.loadAnimation(
                myContext,
                android.R.anim.slide_out_right
            )
        ) //or android.R.anim.fade_out
        //setup the button
        myVFButton = myView.findViewById(R.id.vf_button1)
        myVFButton.setOnClickListener(View.OnClickListener { myViewFlipper.showNext() })
        myVFanim = myView.findViewById(R.id.vf_button2)
        myVFanim.setOnClickListener(View.OnClickListener {
            if (myViewFlipper.isFlipping()) { //is animated so stop it.
                myViewFlipper.stopFlipping()
                myVFanim.setText("Start animation")
            } else {  //start animation
                myViewFlipper.startFlipping()
                //Can also setup how long, with setFlipInterval( int milliseconds)  appears to be 1 second default.
                myVFanim.setText("Stop animation")
            }
        })
        return myView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }
}