package edu.cs4730.guidemo_kt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.guidemo_kt.databinding.ImageFragmentBinding

class Image_Fragment : Fragment() {
    var TAG = "Image_Fragment"
    lateinit var binding: ImageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }
}