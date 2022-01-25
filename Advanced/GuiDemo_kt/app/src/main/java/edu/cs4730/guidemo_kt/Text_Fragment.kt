package edu.cs4730.guidemo_kt

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class Text_Fragment : Fragment() {
    var TAG = "Text_fragment"
    lateinit var tx: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "OnActivityCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Left", "OnCreateView")
        val view = inflater.inflate(R.layout.text_fragment, container, false)
        tx = view.findViewById(R.id.big)
        tx.setText("Large TextView")
        return view
    }
}