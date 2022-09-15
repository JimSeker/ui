package edu.cs4730.supportdesigndemo2_kt


import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * This is the middle fragment, which uses a edittext to put text on the either the left or right
 * fragment in the view pager. It uses a modelview to start the data so the fragments can get via
 * an observer.
 */
class FragMid : Fragment(), View.OnClickListener {
    lateinit var btn_lt: Button
    lateinit var btn_rt: Button
    lateinit var et: EditText
    lateinit var mViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.middle, container, false)
        btn_lt = view.findViewById(R.id.btn_lt)
        btn_lt.setOnClickListener(this)
        btn_rt = view.findViewById(R.id.btn_rt)
        btn_rt.setOnClickListener(this)
        et = view.findViewById(R.id.editText1)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        return view
    }

    override fun onClick(v: View) {
        if (v === btn_lt) { //left button
            mViewModel.appendStrLeft(et.text.toString())
        } else {  //right button
            mViewModel.appendStrRight(et.text.toString())
        }
        et.setText("")
    }
}