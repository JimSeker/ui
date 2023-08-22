package edu.cs4730.recyclerviewdemo3_kt

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import edu.cs4730.recyclerviewdemo3_kt.databinding.InteractiveFragmentBinding
import java.util.ArrayList

class InterActive_Fragment : Fragment() {
    var TAG = "InterActive RV"
    lateinit var binding: InteractiveFragmentBinding
    lateinit var mAdapter: InterActive_myAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = InteractiveFragmentBinding.inflate(inflater, container, false)

        //setup the RecyclerView
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = InterActive_myAdapter(model, R.layout.interactive_rowlayout, requireContext())
        //add the adapter to the recyclerview
        binding.list.adapter = mAdapter
        return binding.list
    }

    // Initially select one of the items
    private val model: List<InterActive_DataModel>
        private get() {
            val list: MutableList<InterActive_DataModel> = ArrayList()
            list.add(InterActive_DataModel("Linux"))
            list.add(InterActive_DataModel("Windows7"))
            list.add(InterActive_DataModel("Suse"))
            list.add(InterActive_DataModel("Eclipse"))
            list.add(InterActive_DataModel("Ubuntu"))
            list.add(InterActive_DataModel("Solaris"))
            list.add(InterActive_DataModel("Android"))
            list.add(InterActive_DataModel("iPhone"))
            // Initially select one of the items
            list[1].isSelected = true
            return list
        }
}