package edu.cs4730.archnavigationdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.cs4730.archnavigationdemo_kt.databinding.FragmentThreeBinding

/**
 * This example receives arguments via the safe_arg version.  Note safe args are included in Project build.grade (not module).
 */
class Fragment_Three : Fragment() {
    lateinit var binding: FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false)

        binding.tvPassed.text = Fragment_ThreeArgs.fromBundle(requireArguments()).message

        val stuff = "Data 2 is " + Fragment_ThreeArgs.fromBundle(requireArguments()).number
        binding.tvPassed2.text = stuff
        return binding.root
    }
}