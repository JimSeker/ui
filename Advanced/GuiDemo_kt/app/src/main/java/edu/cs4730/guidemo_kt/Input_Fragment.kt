package edu.cs4730.guidemo_kt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class Input_Fragment : Fragment(), View.OnClickListener {
    var TAG = "Input_fragment"
    lateinit var myContext: Context
    lateinit var et_single: EditText
    lateinit var et_mutli: EditText
    lateinit var et_pwd: EditText
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "OnActivityCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.input_fragment, container, false)
        et_single = view.findViewById(R.id.et_single)
        et_pwd = view.findViewById(R.id.et_pwd)
        et_pwd.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //doing nothing.
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    // do nothing here.
                }

                override fun afterTextChanged(s: Editable) {
                    // When the text is changed.
                    Toast.makeText(myContext, et_pwd.getText(), Toast.LENGTH_SHORT).show()
                }
            }
        )
        btn = view.findViewById(R.id.button)
        btn.setOnClickListener(this)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }

    override fun onClick(v: View) {
        Toast.makeText(myContext, et_single!!.text, Toast.LENGTH_LONG).show()
    }
}