package edu.cs4730.listdemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class InterActive_ListFragment : ListFragment() {
    var TAG = "InterActive_ListFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.listfragment_layout, container, false)
        val adapter: InterActive_myArrayAdapter = InterActive_myArrayAdapter(
            requireActivity(),
            model
        )
        listAdapter = adapter
        return view
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
            list[1].setSelected(true)
            return list
        }
}