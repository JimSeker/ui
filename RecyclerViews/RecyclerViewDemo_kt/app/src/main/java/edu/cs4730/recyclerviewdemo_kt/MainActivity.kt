package edu.cs4730.recyclerviewdemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Arrays;


/**
 * A very simple example of how to setup a RecyclerView with cards views.
 * A  RecyclerView.Adapter needs to be implemented inorder for it to work.
 * There is not a "simple" adapter, it must be extended.  see myAdapter.
 */
class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: myAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val values = Arrays.asList(
            "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
            "Max OS X", "Linux", "OS/2"
        )

        //setup the RecyclerView
        mRecyclerView = findViewById(R.id.list)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true)

        // use a linear layout manager
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        //and default animator
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = myAdapter(values, R.layout.my_row, this)
        //add the adapter to the recyclerview
        mRecyclerView.adapter = mAdapter
    }
}
