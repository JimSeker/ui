package edu.cs4730.recyclerviewdemo3_kt

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import edu.cs4730.recyclerviewdemo3_kt.databinding.Simple1FragmentBinding

/**
 * A "simple" version of the recyclerview with layout.
 * There is no simple layout or adapter, so both have to be created.
 * Everything is labeled, simple1_ that goes with this one.
 */
class Simple1_Fragment : Fragment() {
    lateinit var binding: Simple1FragmentBinding
    lateinit var mAdapter: Simple1_myAdapter
    var values = arrayOf(
        "Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Simple1FragmentBinding.inflate(inflater, container, false)

        //setup the RecyclerView
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = Simple1_myAdapter(values, R.layout.simple1_rowlayout, requireContext())
        //add the adapter to the recyclerview
        binding.list.adapter = mAdapter
        return binding.root
    }
}