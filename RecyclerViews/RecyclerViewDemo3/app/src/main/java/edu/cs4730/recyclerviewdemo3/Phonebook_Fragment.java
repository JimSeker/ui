package edu.cs4730.recyclerviewdemo3;

/**
 * heavily modified phonebook example, based on the listview named phone_frag example in the listview examples.
 *  design note
 *  yes the remove button is covered by the fab.  this is not a great design example
 *    a swipe to delete should be used instead of a remove button to make this much better design.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo3.databinding.PhonebookFragmentBinding;

public class Phonebook_Fragment extends Fragment {

    String TAG = "Phone_Fragment";
    List<Phonebook_DataModel> listOfPhonebook;
    PhonebookFragmentBinding binding;
    Phonebook_myAdapter mAdapter;


    public Phonebook_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PhonebookFragmentBinding.inflate(inflater, container, false);

        //setup a simple list of data to start with.
        listOfPhonebook = setup();

        //fab and coordinatorlayout setup.
        FloatingActionButton fab;
                binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(binding.coordinatorlayout1, "add is not implemented.", Snackbar.LENGTH_LONG)
                    .show();
            }
        });

        //setup the RecyclerView
        binding.list.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Phonebook_myAdapter(listOfPhonebook, R.layout.phonebook_rowlayout, requireContext());
        //add the adapter to the recyclerview
        binding.list.setAdapter(mAdapter);

        return binding.getRoot();
    }


    List<Phonebook_DataModel> setup() {
        List<Phonebook_DataModel> list = new ArrayList<Phonebook_DataModel>();
        list.add(new Phonebook_DataModel("Test", "9981728", "test@test.com"));
        list.add(new Phonebook_DataModel("Test1", "1234455", "test1@test.com"));
        list.add(new Phonebook_DataModel("Test2", "00000", "test2@test.com"));
        list.add(new Phonebook_DataModel("Test3", "00000", "test3@test.com"));
        list.add(new Phonebook_DataModel("Test4", "00000", "test4test.com"));
        list.add(new Phonebook_DataModel("Test5", "00000", "test5@test.com"));
        list.add(new Phonebook_DataModel("Test6", "00000", "test6@test.com"));
        return list;
    }
}

