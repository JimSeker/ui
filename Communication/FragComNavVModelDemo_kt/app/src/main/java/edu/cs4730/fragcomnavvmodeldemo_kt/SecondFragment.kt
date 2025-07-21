package edu.cs4730.fragcomnavvmodeldemo_kt

import androidx.navigation.Navigation.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.fragcomnavvmodeldemo_kt.databinding.FragmentSecondBinding

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        binding.btn1.setOnClickListener { v ->
            //this is calling the interface, which call into the activity, so it
            mViewModel.num_one++
            mViewModel.setItem("Called by SecondFragment")
            findNavController(v).navigate(R.id.action_second_to_first)
        }
        binding.tv1.text = "Parameter1: " + mViewModel.num_two
        mViewModel.data.observe(viewLifecycleOwner) { data ->
            binding.tv2.text = "Parameter2: $data"
        }
        return binding.root
    }
}