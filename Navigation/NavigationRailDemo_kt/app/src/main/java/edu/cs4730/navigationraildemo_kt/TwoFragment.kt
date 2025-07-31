package edu.cs4730.navigationraildemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.navigationraildemo_kt.databinding.FragmentTwoBinding

/**
 * A simple [Fragment] subclass.
 */
class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }
}