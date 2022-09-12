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
class FirstFragment : Fragment() {
    lateinit var tv1: TextView
    lateinit var tv2: TextView
    lateinit var btn1: Button
    lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_first, container, false)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        tv1 = myView.findViewById(R.id.ff_tv1)
        tv2 = myView.findViewById(R.id.ff_tv2)
        btn1 = myView.findViewById(R.id.ff_btn1)
        btn1.setOnClickListener(View.OnClickListener { v -> //this is call the navigation to change the second fragment
            mViewModel.num_two++
            mViewModel.setItem("Called by FirstFragment")
            findNavController(v).navigate(R.id.action_first_to_second)
        })
        mViewModel.data.observe(viewLifecycleOwner) { data -> tv2.text = "Parameter2: $data" }
        tv1.text = "Parameter1: " + mViewModel.num_one
        return myView
    }
}