package edu.cs4730.recyclerviewdemo2_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mAdapter: phoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup data.
        val listOfPhonebook: MutableList<Phonebook> = ArrayList()
        listOfPhonebook.add(Phonebook("Test", "9981728", "test@test.com"))
        listOfPhonebook.add(Phonebook("Test1", "1234455", "test1@test.com"))
        listOfPhonebook.add(Phonebook("Test2", "00000", "test2@test.com"))
        listOfPhonebook.add(Phonebook("Test3", "00000", "test3@test.com"))
        listOfPhonebook.add(Phonebook("Test4", "00000", "test4test.com"))
        listOfPhonebook.add(Phonebook("Test5", "00000", "test5@test.com"))
        listOfPhonebook.add(Phonebook("Test6", "00000", "test6@test.com"))

        //setup the RecyclerView
        mRecyclerView = findViewById(R.id.list)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))
        mRecyclerView.setItemAnimator(DefaultItemAnimator())
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = phoneAdapter(listOfPhonebook, R.layout.phone_row, this)
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter)
    }

}