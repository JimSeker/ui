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
 * A simple [Fragment] subclass.
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
            mViewModel.incr_One()
            mViewModel.setItem( "Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_first)
        }
        //button to call secondfragment.
        btn2 = myView.findViewById(R.id.button2)
        btn2.setOnClickListener(View.OnClickListener { v -> //this is calling the navigation to call the action to change to second fragment.
            mViewModel.incr_Two()
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_second)
        })
        mViewModel.getoneLD().observe(viewLifecycleOwner) { v -> tv1.text = "Parameter1: $v" }
        mViewModel.gettwoLD().observe(viewLifecycleOwner) { v -> tv2.text = "Parameter1: $v" }
        return myView
    }
}