package edu.cs4730.viewpagerdemo_kt

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.viewpagerdemo_kt.databinding.LeftBinding

/**
 * This is a simple fragment to display data and it the "left" most fragment in the viewpager.
 * The code here is identical to the code in the right fragment.
 */
class FragLeft : Fragment() {
    lateinit var binding: LeftBinding
    lateinit var mViewModel: DataViewModel
    var TAG = "Left"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate")
        if (savedInstanceState != null) {
            Log.d(TAG, "OnCreate savedInstanceState")
        }
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d("Left", "OnCreateView")
        binding = LeftBinding.inflate(inflater, container, false)

        mViewModel.dataLeft.observe(viewLifecycleOwner) { data -> binding.tvleft.text = data }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "OnSaveInstanceState")
        super.onSaveInstanceState(outState)
    }
}