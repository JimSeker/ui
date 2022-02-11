package edu.cs4730.navdrawerfragviewmodeldemo_kt

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider


/**
 * This is a simple fragment used to display the data for whichever shakespeare that is clicked on
 * in the titlefrag fragment.  it uses a viewmodel, so everything is received from there.
 */
class textFrag : Fragment() {
    lateinit var tv: TextView
    lateinit var mViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.text_fragment, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        tv = view.findViewById(R.id.text)
        mViewModel.data.observe(requireActivity()) { integer ->
            tv.setText(
                Shakespeare.DIALOGUE[integer!!]
            )
        }
        return view
    }
}