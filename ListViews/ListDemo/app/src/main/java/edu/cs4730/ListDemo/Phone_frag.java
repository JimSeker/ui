package edu.cs4730.ListDemo;

/*
 * This code is copied from http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
 */

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Phone_frag extends Fragment {
	
    String TAG = "Phone_Fragment";
    Context myContext;
	
	public Phone_frag() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.phone_frag, container, false);

        ListView list = (ListView) myView.findViewById(R.id.ListView01);
        list.setClickable(true);

        final List<Phonebook> listOfPhonebook = new ArrayList<Phonebook>();
        listOfPhonebook.add(new Phonebook("Test", "9981728", "test@test.com"));
        listOfPhonebook.add(new Phonebook("Test1", "1234455", "test1@test.com"));
        listOfPhonebook.add(new Phonebook("Test2", "00000", "test2@test.com"));
        listOfPhonebook.add(new Phonebook("Test3", "00000", "test3@test.com"));
        listOfPhonebook.add(new Phonebook("Test4", "00000", "test4test.com"));
        listOfPhonebook.add(new Phonebook("Test5", "00000", "test5@test.com"));
        listOfPhonebook.add(new Phonebook("Test6", "00000", "test6@test.com"));

        PhonebookAdapter adapter = new PhonebookAdapter(myContext, listOfPhonebook);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                showToast(listOfPhonebook.get(position).getName());
            }
        });

        list.setAdapter(adapter);
        
        return myView;
    }

    private void showToast(String message) {
        Toast.makeText(myContext, message, Toast.LENGTH_LONG).show();
    }
    

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myContext = activity.getApplicationContext();
		Log.d(TAG,"onAttach");
	}

	
}

