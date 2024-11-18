package edu.cs4730.recyclerviewdemo2_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import edu.cs4730.recyclerviewdemo2_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: phoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
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
        binding.list.setHasFixedSize(true)
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.itemAnimator = DefaultItemAnimator()
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = phoneAdapter(listOfPhonebook, R.layout.phone_row, this)
        //add the adapter to the recyclerview
        binding.list.adapter = mAdapter
    }

}