package edu.cs4730.listdemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment
import edu.cs4730.listdemo_kt.databinding.ListfragmentLayoutBinding

class Simple1_ListFragment : ListFragment() {
    var TAG = "Simple_frag"
    var values = arrayOf(
        "Android",        "iPhone",        "WindowsMobile",        "Blackberry",
        "WebOS",        "Ubuntu",        "Windows7",        "Max OS X",
        "Linux",        "OS/2",        "VMS",        "DOS",
        "Other",        "Chrome"
    )
    lateinit var binding: ListfragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ListfragmentLayoutBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, values)
        listAdapter = adapter
        return binding.root
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        Toast.makeText(requireContext(), "${values[position]} selected", Toast.LENGTH_LONG).show()
    }
}

