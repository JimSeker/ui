package edu.cs4730.menudemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import edu.cs4730.menudemo_kt.databinding.FragmenuBinding

class FragMenu2 : Fragment() {
    lateinit var binding: FragmenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmenuBinding.inflate(inflater, container, false)
        binding.tv.text = "Fragment #2"

        //add menu items to the fragment.
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.frag2menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.frag2item) {
                    Toast.makeText(requireContext(), "Fragment #1", Toast.LENGTH_LONG).show()
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

}