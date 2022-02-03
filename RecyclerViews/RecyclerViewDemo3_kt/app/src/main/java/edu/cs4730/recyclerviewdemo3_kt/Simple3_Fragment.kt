package edu.cs4730.recyclerviewdemo3_kt

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import java.util.*

/**
 * A third "simple" example of a recycler view.   Everything with this example is prefix simple3_
 * this uses a cardview as the layout, so it looks "better" and you see where each view is separated.
 */
class Simple3_Fragment : Fragment() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: Simple3_myAdapter
    var values = Arrays.asList(
        "Android", "iPhone", "WindowsMobile",
        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.simple3_fragment, container, false)
        //setup the RecyclerView
        mRecyclerView = myView.findViewById<View>(R.id.list) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = Simple3_myAdapter(values, R.layout.simple3_rowlayout, requireContext())
        //add the adapter to the recyclerview
        mRecyclerView.adapter = mAdapter
        return myView
    }
}