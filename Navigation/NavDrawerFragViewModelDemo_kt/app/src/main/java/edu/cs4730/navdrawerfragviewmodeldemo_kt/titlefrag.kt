package edu.cs4730.navdrawerfragviewmodeldemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import edu.cs4730.navdrawerfragviewmodeldemo_kt.databinding.ListfragmentLayoutBinding

/**
 * this ia listfragment.  All we need to do is setlistadapter in onCreateView
 * and override onListItemClick.    using the viewmodel setup the data.
 */
class titlefrag : ListFragment() {
    lateinit var mViewModel: DataViewModel
    lateinit var binding: ListfragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ListfragmentLayoutBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, Shakespeare.TITLES)
        listAdapter = adapter
        return binding.root
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)
        mViewModel.item = id.toInt()
    }
}