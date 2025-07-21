package edu.cs4730.fragcomnavvmodeldemo_kt

import androidx.navigation.Navigation.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.fragcomnavvmodeldemo_kt.databinding.FragmentMainBinding

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        //button to call firstfragment
        binding.button1.setOnClickListener { v ->
            //this is calling the navigation to call the action to change to first fragment.
            //can change to the first fragment and send a simple string as well.
            mViewModel.num_one++
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_first)
        }
        //button to call secondfragment.
        binding.button2.setOnClickListener { v ->
            //this is calling the navigation to call the action to change to second fragment.
            mViewModel.num_two++
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_second)
        }
        binding.tv1.text = "Parameter1: " + mViewModel.num_one
        binding.tv2.text = "Parameter2: " + mViewModel.num_two
        return binding.root
    }
}