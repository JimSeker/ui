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
import edu.cs4730.guidemo_kt.databinding.ViewswitchFragmentBinding

/**
 * This shows how the view switcher can work.
 * It's in interesting one, because we can change a "static" layout pretty easy.
 */
class ViewSwitch_Fragment : Fragment() {
    var TAG = "ViewSwitch_fragment"
    lateinit var binding: ViewswitchFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ViewswitchFragmentBinding.inflate(inflater, container, false)
        //setup the button
        binding.vsButton.setOnClickListener { binding.viewSwitcher1.showNext() }

        //ViewFlipper code
        //set animation, which can be used in viewswitcher as well
        binding.viewFlipper1.inAnimation = AnimationUtils.loadAnimation(
            requireContext(),
            android.R.anim.slide_in_left
        ) //or android.R.anim.fade_in
        binding.viewFlipper1.outAnimation = AnimationUtils.loadAnimation(
            requireContext(),
            android.R.anim.slide_out_right
        ) //or android.R.anim.fade_out
        //setup the button

        binding.vfButton1.setOnClickListener { binding.viewFlipper1.showNext() }

        binding.VFanim.setOnClickListener {
            if (binding.viewFlipper1.isFlipping) { //is animated so stop it.
                binding.viewFlipper1.stopFlipping()
                binding.VFanim.text = "Start animation"
            } else {  //start animation
                binding.viewFlipper1.startFlipping()
                //Can also setup how long, with setFlipInterval( int milliseconds)  appears to be 1 second default.
                binding.VFanim.text = "Stop animation"
            }
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }
}