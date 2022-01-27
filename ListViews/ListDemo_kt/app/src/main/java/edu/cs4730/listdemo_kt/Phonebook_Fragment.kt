package edu.cs4730.listdemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

/*
 * This code is copied from http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
 */



class Phonebook_Fragment : Fragment() {
    var TAG = "Phone_Fragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView: View = inflater.inflate(R.layout.phonebook_fragment, container, false)
        val list = myView.findViewById<ListView>(R.id.ListView01)
        list.isClickable = true
        val listOfPhonebook: MutableList<Phonebook_DataModel> = ArrayList()
        listOfPhonebook.add(Phonebook_DataModel("Test", "9981728", "test@test.com"))
        listOfPhonebook.add(Phonebook_DataModel("Test1", "1234455", "test1@test.com"))
        listOfPhonebook.add(Phonebook_DataModel("Test2", "00000", "test2@test.com"))
        listOfPhonebook.add(Phonebook_DataModel("Test3", "00000", "test3@test.com"))
        listOfPhonebook.add(Phonebook_DataModel("Test4", "00000", "test4test.com"))
        listOfPhonebook.add(Phonebook_DataModel("Test5", "00000", "test5@test.com"))
        listOfPhonebook.add(Phonebook_DataModel("Test6", "00000", "test6@test.com"))
        val adapter = Phonebook_myAdapter(requireContext(), listOfPhonebook)
        list.onItemClickListener =
            OnItemClickListener { arg0, view, position, index -> //showToast(listOfPhonebook.get(position).getName());
                Toast.makeText(requireContext(), listOfPhonebook[position].name, Toast.LENGTH_LONG)
                    .show()
            }
        list.adapter = adapter
        return myView
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}

