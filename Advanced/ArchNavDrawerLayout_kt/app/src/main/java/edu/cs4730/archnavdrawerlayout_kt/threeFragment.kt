package edu.cs4730.archnavdrawerlayout_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.archnavdrawerlayout_kt.databinding.FragmentThreeBinding

/**
 * A simple [Fragment] subclass.
 */
class threeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }
}