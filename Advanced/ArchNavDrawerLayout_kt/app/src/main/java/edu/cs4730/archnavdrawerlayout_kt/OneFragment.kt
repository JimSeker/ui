package edu.cs4730.archnavdrawerlayout_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.archnavdrawerlayout_kt.databinding.FragmentOneBinding

/**
 * A simple [Fragment] subclass.
 */
class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }
}