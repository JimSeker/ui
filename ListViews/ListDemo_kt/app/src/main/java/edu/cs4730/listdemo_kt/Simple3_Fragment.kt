package edu.cs4730.listdemo_kt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.cs4730.listdemo_kt.R
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment

class Simple3_Fragment : Fragment() {
    var TAG = "Simple3_Fragment"
    lateinit var list: ListView
    lateinit var up: Button
    lateinit var down: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.simple3_fragment, container, false)
        list = myView.findViewById<View>(R.id.ListView01) as ListView
        list.isClickable = true
        val values = arrayOf(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, values
        )
        list.adapter = adapter
        list.onItemClickListener = OnItemClickListener { arg0, view, position, index ->
            val item = list.adapter.getItem(position).toString()
            Toast.makeText(requireContext(), "$item selected", Toast.LENGTH_LONG).show()
        }
        up = myView.findViewById(R.id.up)
        up.setOnClickListener(View.OnClickListener { // up button, so move the list up
            val i = list.selectedItemPosition
            //Toast.makeText(simple3.this, "up selected is " + i,Toast.LENGTH_LONG).show();
            if (i == ListView.INVALID_POSITION) { // nothing selected, so select first position.
                list.setSelection(0)
            }
            if (i > 0) {
                list.setSelection(i - 1)
            }
        })
        down = myView.findViewById(R.id.down)
        down.setOnClickListener(View.OnClickListener { // down button, so move the it down
            val i = list.selectedItemPosition
            //Toast.makeText(simple3.this, "down selected is " + i, Toast.LENGTH_LONG).show();
            list.setSelection(list.count)
        })
        return myView
    }
}