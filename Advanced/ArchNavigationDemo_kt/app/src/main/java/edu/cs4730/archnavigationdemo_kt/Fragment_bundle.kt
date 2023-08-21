package edu.cs4730.archnavigationdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.archnavigationdemo_kt.databinding.FragmentBundleBinding

/**
 * This is a simple fragment to show use to receive a bundle via the nav_graph.  see fragment_two for the
 * bundle creation and send.
 */
class Fragment_bundle : Fragment() {
    private lateinit var binding: FragmentBundleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBundleBinding.inflate(inflater, container, false)

        //via a bundle, instead of safe args
        binding.btvPassed.text = requireArguments().getString("message", "no data")
        val stuff = "Data 2 is " + requireArguments().getInt("number", 1)
        binding.btvPassed2.text = stuff
        return binding.root
    }
}