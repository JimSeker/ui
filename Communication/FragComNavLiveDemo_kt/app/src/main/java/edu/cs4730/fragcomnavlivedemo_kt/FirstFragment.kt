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

/**
 *
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
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        mViewModel.getoneLD().observe(viewLifecycleOwner) { v -> tv1.text = "Parameter1: $v" }
        mViewModel.itemLD.observe(viewLifecycleOwner) { s -> tv2.text = "Parameter1: $s" }
        tv1 = myView.findViewById(R.id.ff_tv1)
        tv2 = myView.findViewById(R.id.ff_tv2)

        btn1 = myView.findViewById(R.id.ff_btn1)
        btn1.setOnClickListener { v -> //this is call the navigation to change the second fragment
            mViewModel.incr_Two()
            mViewModel.setItem("Called by FirstFramgnet")
            findNavController(v).navigate(R.id.action_first_to_second)
        }
        return myView
    }
}