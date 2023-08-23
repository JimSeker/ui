package edu.cs4730.fragcomnavvmodeldemo_kt

import androidx.navigation.Navigation.findNavController
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.fragcomnavvmodeldemo_kt.databinding.FragmentFirstBinding

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        binding.btn1.setOnClickListener(View.OnClickListener { v ->
            //this is call the navigation to change the second fragment
            mViewModel.num_two++
            mViewModel.setItem("Called by FirstFragment")
            findNavController(v).navigate(R.id.action_first_to_second)
        })
        mViewModel.data.observe(viewLifecycleOwner) { data ->
            binding.tv2.text = "Parameter2: $data"
        }
        binding.tv1.text = "Parameter1: " + mViewModel.num_one
        return binding.root
    }
}