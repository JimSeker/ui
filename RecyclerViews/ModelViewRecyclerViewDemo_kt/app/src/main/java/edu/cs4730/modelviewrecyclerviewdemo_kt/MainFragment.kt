package edu.cs4730.modelviewrecyclerviewdemo_kt

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.lifecycle.ViewModelProvider
import java.util.*

/**
 * A simple fragment that displays a recyclerview
 *
 *
 * It does setup the ViewModel observer to see when the data changes, and logs it as part of
 * the test to how it could work.
 */
class MainFragment : Fragment() {
    lateinit var mViewModel: DataViewModel
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: myAdapter
    var TAG = "MainFragment"
    private val mList: List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.fragment_main, container, false)
        mRecyclerView = myView.findViewById(R.id.listtrans)
        mRecyclerView.setLayoutManager(LinearLayoutManager(activity))
        mRecyclerView.setItemAnimator(DefaultItemAnimator())
        mAdapter = myAdapter(mList, R.layout.row_layout, this)
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter)

        //needed the activity, Doc's Creates a ViewModelProvider, which retains ViewModels while a scope of given Activity is alive.
        //otherwise, activity is not the same instance and so not triggered either.
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        mViewModel.itemLD.observe(viewLifecycleOwner) { s -> Log.d(TAG, "triggered $s") }
        return myView
    }

    init {
        mList = Arrays.asList(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )
    }
}