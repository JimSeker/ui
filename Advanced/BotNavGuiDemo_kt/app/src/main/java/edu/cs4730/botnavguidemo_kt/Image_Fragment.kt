package edu.cs4730.botnavguidemo_kt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

class Image_Fragment : Fragment() {
    var TAG = "Image_Fragment"
    lateinit var myContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }
}