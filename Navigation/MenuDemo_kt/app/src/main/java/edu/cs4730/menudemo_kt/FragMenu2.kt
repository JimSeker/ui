package edu.cs4730.menudemo_kt

import android.os.Bundle
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragMenu2 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //required if the fragment is adding menu items, otherwise it calls the menu methods.
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragmenu, container, false)
        val tv = view.findViewById<View>(R.id.tv) as TextView
        tv.text = "Fragment #2"
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.frag2menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.frag2item) {
            Toast.makeText(requireActivity().applicationContext, "Fragment #2", Toast.LENGTH_LONG)
                .show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}