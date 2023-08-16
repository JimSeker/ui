package edu.cs4730.guidemo_kt

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.guidemo_kt.databinding.TextFragmentBinding

class Text_Fragment : Fragment() {
    var TAG = "Text_fragment"
    lateinit var binding: TextFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Left", "OnCreateView")
        binding = TextFragmentBinding.inflate(inflater, container, false)

        binding.big.text = "Large TextView"
        return binding.root
    }
}