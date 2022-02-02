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

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Phonebook_Fragment extends Fragment {

    String TAG = "Phone_Fragment";
    List<Phonebook_DataModel> listOfPhonebook;
    RecyclerView mRecyclerView;
    Phonebook_myAdapter mAdapter;
    //getting a cast except if I don't fully declare it.
    androidx.coordinatorlayout.widget.CoordinatorLayout mCoordinatorLayout;

    public Phonebook_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.phonebook_fragment, container, false);

        //setup a simple list of data to start with.
        listOfPhonebook = setup();

        //fab and coordinatorlayout setup.
        FloatingActionButton fab;
        mCoordinatorLayout = (androidx.coordinatorlayout.widget.CoordinatorLayout) myView.findViewById(R.id.coordinatorlayout1);
        myView.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mCoordinatorLayout, "add is not implemented.", Snackbar.LENGTH_LONG)
                    .show();
            }
        });

        //setup the RecyclerView
        mRecyclerView = myView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Phonebook_myAdapter(listOfPhonebook, R.layout.phonebook_rowlayout, requireContext());
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        return myView;
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

