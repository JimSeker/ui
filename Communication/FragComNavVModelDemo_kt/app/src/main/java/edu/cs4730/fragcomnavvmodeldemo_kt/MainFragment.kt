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
class MainFragment : Fragment() {
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var mViewModel: DataViewModel
    lateinit var tv1: TextView
    lateinit var tv2: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_main, container, false)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        tv1 = myView.findViewById(R.id.textView3)
        tv2 = myView.findViewById(R.id.textView4)
        //button to call firstfragment
        btn1 = myView.findViewById(R.id.button1)
        btn1.setOnClickListener { v -> //this is calling the navigation to call the action to change to first fragment.
            //can change to the first fragment and send a simple string as well.
            mViewModel.num_one++
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_first)
        }
        //button to call secondfragment.
        btn2 = myView.findViewById(R.id.button2)
        btn2.setOnClickListener { v -> //this is calling the navigation to call the action to change to second fragment.
            mViewModel.num_two++
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_second)
        }
        tv1.text = "Parameter1: " + mViewModel.num_one
        tv2.text = "Parameter2: " + mViewModel.num_two
        return myView
    }
}