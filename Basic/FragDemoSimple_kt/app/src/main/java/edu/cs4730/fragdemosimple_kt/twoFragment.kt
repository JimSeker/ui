package edu.cs4730.fragdemosimple_kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.cs4730.fragdemosimple_kt.databinding.FragmentTwoBinding

/**
 * A simple fragment class.  this is the most basic code.  it does nothing.
 */
class twoFragment : Fragment() {
    private lateinit var binding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }
}
