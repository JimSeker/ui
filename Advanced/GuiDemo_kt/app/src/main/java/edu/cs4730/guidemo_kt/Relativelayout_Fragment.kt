package edu.cs4730.guidemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class Relativelayout_Fragment : Fragment() {
    var TAG = "Relativelayout_Fragment"
    lateinit var myContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView: View
        myView = inflater.inflate(R.layout.relativelayout_fragment, container, false)
        return myView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }
}