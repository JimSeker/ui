package edu.cs4730.archnavigationdemo_kt

import androidx.navigation.Navigation.createNavigateOnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * This fragment is the "main" fragment that is shown first.
 */
class FragmentMain : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_main, container, false)
        val btn = myView.findViewById<Button>(R.id.button)

        //add transaction for the button or the convenience method below (uncommented).
        //btn.setOnClickListener { view -> findNavController(view).navigate(R.id.action_fragmentMain_to_fragment_two) }
        //or convenience method
        btn.setOnClickListener(
            createNavigateOnClickListener(
                R.id.action_fragmentMain_to_fragment_two,
                null
            )
        )
        return myView
    }
}