package edu.cs4730.listdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import edu.cs4730.listdemo_kt.databinding.Simple3FragmentBinding

class Simple3_Fragment : Fragment() {
    var TAG = "Simple3_Fragment"
    lateinit var binding: Simple3FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = Simple3FragmentBinding.inflate(inflater, container, false)

        binding.ListView01.isClickable = true
        val values = arrayOf(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2", "VMS", "DOS", "Other", "Chrome"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, values
        )
        binding.ListView01.adapter = adapter
        binding.ListView01.onItemClickListener =
            OnItemClickListener { arg0, view, position, index ->
                val item = binding.ListView01.adapter.getItem(position).toString()
                Toast.makeText(requireContext(), "$item selected", Toast.LENGTH_LONG).show()
            }

        binding.up.setOnClickListener { // up button, so move the list up
            val i = binding.ListView01.selectedItemPosition
            //Toast.makeText(requireContext(), "up selected is " + i,Toast.LENGTH_LONG).show();
            if (i == ListView.INVALID_POSITION) { // nothing selected, so select first position.
                binding.ListView01.setSelection(0)
            }
            if (i > 0) {
                binding.ListView01.setSelection(i - 1)
            }
        }

        binding.down.setOnClickListener { // down button, so move the it down
            //val i = list.selectedItemPosition
            //Toast.makeText(simple3.this, "down selected is " + i, Toast.LENGTH_LONG).show();
            binding.ListView01.setSelection(binding.ListView01.count)
        }
        return binding.root
    }
}