package edu.cs4730.recyclerviewdemo3_kt

import androidx.recyclerview.widget.RecyclerView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import java.util.ArrayList

/**
 * heavily modified phonebook example, based on the listview named phone_frag example in the listview examples.
 * design note
 * yes the remove button is covered by the fab.  this is not a great design example
 * a swipe to delete should be used instead of a remove button to make this much better design.
 */
class Phonebook_Fragment : Fragment() {
    var TAG = "Phone_Fragment"
    lateinit var listOfPhonebook: MutableList<Phonebook_DataModel>
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: Phonebook_myAdapter

    //getting a cast except if I don't fully declare it.
    var mCoordinatorLayout: CoordinatorLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.phonebook_fragment, container, false)

        //setup a simple list of data to start with.
        listOfPhonebook = setup()

        //fab and coordinatorlayout setup.
        var fab: FloatingActionButton
        mCoordinatorLayout = myView.findViewById<View>(R.id.coordinatorlayout1) as CoordinatorLayout
        myView.findViewById<View>(R.id.fab).setOnClickListener {
            Snackbar.make(mCoordinatorLayout!!, "add is not implemented.", Snackbar.LENGTH_LONG)
                .show()
        }

        //setup the RecyclerView
        mRecyclerView = myView.findViewById(R.id.list)
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter =
            Phonebook_myAdapter(listOfPhonebook, R.layout.phonebook_rowlayout, requireContext())
        //add the adapter to the recyclerview
        mRecyclerView.adapter = mAdapter
        return myView
    }

    fun setup(): MutableList<Phonebook_DataModel> {
        val list: MutableList<Phonebook_DataModel> = ArrayList()
        list.add(Phonebook_DataModel("Test", "9981728", "test@test.com"))
        list.add(Phonebook_DataModel("Test1", "1234455", "test1@test.com"))
        list.add(Phonebook_DataModel("Test2", "00000", "test2@test.com"))
        list.add(Phonebook_DataModel("Test3", "00000", "test3@test.com"))
        list.add(Phonebook_DataModel("Test4", "00000", "test4test.com"))
        list.add(Phonebook_DataModel("Test5", "00000", "test5@test.com"))
        list.add(Phonebook_DataModel("Test6", "00000", "test6@test.com"))
        return list
    }
}