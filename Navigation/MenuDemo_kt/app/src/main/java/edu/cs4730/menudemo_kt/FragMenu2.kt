package edu.cs4730.menudemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.menudemo_kt.databinding.FragmenuBinding

class FragMenu2 : Fragment() {
    lateinit var binding: FragmenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //required if the fragment is adding menu items, otherwise it calls the menu methods.
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmenuBinding.inflate(inflater, container, false)
        binding.tv.text = "Fragment #2"
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.frag2menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.frag2item) {
            Toast.makeText(requireContext(), "Fragment #2", Toast.LENGTH_LONG)
                .show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}