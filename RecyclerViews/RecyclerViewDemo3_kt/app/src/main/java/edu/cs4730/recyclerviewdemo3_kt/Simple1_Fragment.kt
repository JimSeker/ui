package edu.cs4730.recyclerviewdemo3_kt

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator

/**
 * A "simple" version of the recyclerview with layout.
 * There is no simple layout or adapter, so both have to be created.
 * Everything is labeled, simple1_ that goes with this one.
 */
class Simple1_Fragment : Fragment() {
    lateinit var mRecyclerView: RecyclerView
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
        val myView = inflater.inflate(R.layout.simple1_fragment, container, false)

        //setup the RecyclerView
        mRecyclerView = myView.findViewById(R.id.list)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = Simple1_myAdapter(values, R.layout.simple1_rowlayout, requireContext())
        //add the adapter to the recyclerview
        mRecyclerView.adapter = mAdapter
        return myView
    }
}