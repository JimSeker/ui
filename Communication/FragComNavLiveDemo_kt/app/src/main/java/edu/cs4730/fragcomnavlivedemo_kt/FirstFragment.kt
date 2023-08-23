package edu.cs4730.fragcomnavlivedemo_kt

import androidx.navigation.Navigation.findNavController
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.fragcomnavlivedemo_kt.databinding.FragmentFirstBinding

/**
 *
 */
class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        mViewModel.getoneLD().observe(viewLifecycleOwner) { v -> binding.tv1.text = "Parameter1: $v" }
        mViewModel.itemLD.observe(viewLifecycleOwner) { s -> binding.tv2.text = "Parameter1: $s" }


        binding.btn1.setOnClickListener { v -> //this is call the navigation to change the second fragment
            mViewModel.incr_Two()
            mViewModel.setItem("Called by FirstFramgnet")
            findNavController(v).navigate(R.id.action_first_to_second)
        }
        return binding.root
    }
}