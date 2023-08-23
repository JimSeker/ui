package edu.cs4730.simplefragcomdemo_kt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.simplefragcomdemo_kt.databinding.FragmentInfoBinding

/**
 * a simple fragment to display data.
 */
class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private var num = 0
    private var attached = false

    fun update(i: Int) {
        num += i
        if (attached)
            binding.numclick.text = "Number of clicks: $num"
        else
            Log.v("Info", "label is available yet")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        binding.numclick.text = "Number of clicks: $num"
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        attached = true
    }

    override fun onDetach() {
        super.onDetach()
        attached = false
    }
}