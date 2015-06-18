package edu.cs4730.recyclerviewdemo3;

/*
 * heavily modified phonebook example, based on the listview named phone_frag example in the listview examples.
 *
 */

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Phonebook_Fragment extends Fragment {
	
    String TAG = "Phone_Fragment";
    Context myContext;
    List<Phonebook_DataModel> listOfPhonebook;
    RecyclerView mRecyclerView;
    Phonebook_myAdapter mAdapter;


	public Phonebook_Fragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.phonebook_fragment, container, false);


        //setup a simple list of data to start with.
        listOfPhonebook = setup();


        //setup the RecyclerView
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(myContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new Phonebook_myAdapter(listOfPhonebook, R.layout.phonebook_rowlayout, myContext);
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);
        
        return myView;
    }

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myContext = activity.getApplicationContext();
		Log.d(TAG, "onAttach");
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

