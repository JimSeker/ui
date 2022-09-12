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

/**
 * simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 * Note it would even better with LiveData, but that is another example.
 */
class SecondFragment : Fragment() {
    lateinit var tv1: TextView
    lateinit var tv2: TextView
    lateinit var btn1: Button
    lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_second, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        tv1 = myView.findViewById(R.id.sf_tv1)
        tv2 = myView.findViewById(R.id.sf_tv2)
        btn1 = myView.findViewById(R.id.sf_btn1)
        btn1.setOnClickListener { v -> //this is calling the interface, which call into the activity, so it
            mViewModel.num_one++
            mViewModel.setItem("Called by SecondFragment")
            findNavController(v).navigate(R.id.action_second_to_first)
        }
        tv1.text = "Parameter1: " + mViewModel.num_two
        mViewModel.data.observe(viewLifecycleOwner) { data -> tv2.text = "Parameter2: $data" }
        return myView
    }
}