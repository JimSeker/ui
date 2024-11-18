package edu.cs4730.recyclerviewdemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import edu.cs4730.recyclerviewdemo_kt.databinding.ActivityMainBinding
import java.util.Arrays


/**
 * A very simple example of how to setup a RecyclerView with cards views.
 * A  RecyclerView.Adapter needs to be implemented inorder for it to work.
 * There is not a "simple" adapter, it must be extended.  see myAdapter.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: myAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        val values = Arrays.asList(
            "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
            "Max OS X", "Linux", "OS/2"
        )

        //setup the RecyclerView

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.list.setHasFixedSize(true)

        // use a linear layout manager
        binding.list.layoutManager = LinearLayoutManager(this)
        //and default animator
        binding.list.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = myAdapter(values, R.layout.my_row, this)
        //add the adapter to the recyclerview
        binding.list.adapter = mAdapter
    }
}
