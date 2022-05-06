package edu.cs4730.archnavigationdemo_kt

import androidx.navigation.Navigation.findNavController
import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * Second fragment to seen by the use.  This also shows how to pass data via a bundle or safe_args.
 */
class Fragment_two : Fragment() {
    lateinit var et: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_two, container, false)
        myView.findViewById<View>(R.id.two_back)
            .setOnClickListener { view ->
                findNavController(view).navigateUp() //go back!
            }
        //now for passing data.
        et = myView.findViewById(R.id.editText)
        val btn = myView.findViewById<Button>(R.id.two_next)
        //add transaction for the button.
        btn.setOnClickListener { view -> //get the "action" and add the parameters to it.  Then navigate to it.
            val action = Fragment_twoDirections.actionFragmentTwoToFragmentThree()
            action.message = et.text.toString()
            action.number = 3012
            findNavController(view).navigate(action)
        }
        val btn2 = myView.findViewById<Button>(R.id.two_next_bundle)
        //add transaction for the button.
        btn2.setOnClickListener { view -> //this uses a bundle instead of safe args.  Also is a lot simpler.  No addition info in needed in nav.
            val bundle = Bundle()
            bundle.putString("message", et.getText().toString())
            bundle.putInt("number", 3012)
            findNavController(view).navigate(R.id.action_two_to_bundle, bundle)
        }
        return myView
    }
}