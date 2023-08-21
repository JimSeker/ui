package edu.cs4730.recyclerviewdemo2;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
   ActivityMainBinding binding;
    phoneAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setup data.
        final List<Phonebook> listOfPhonebook = new ArrayList<Phonebook>();
        listOfPhonebook.add(new Phonebook("Test", "9981728", "test@test.com"));
        listOfPhonebook.add(new Phonebook("Test1", "1234455", "test1@test.com"));
        listOfPhonebook.add(new Phonebook("Test2", "00000", "test2@test.com"));
        listOfPhonebook.add(new Phonebook("Test3", "00000", "test3@test.com"));
        listOfPhonebook.add(new Phonebook("Test4", "00000", "test4test.com"));
        listOfPhonebook.add(new Phonebook("Test5", "00000", "test5@test.com"));
        listOfPhonebook.add(new Phonebook("Test6", "00000", "test6@test.com"));

        //setup the RecyclerView
        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new phoneAdapter(listOfPhonebook, R.layout.phone_row, this);
        //add the adapter to the recyclerview
        binding.list.setAdapter(mAdapter);
    }

}
