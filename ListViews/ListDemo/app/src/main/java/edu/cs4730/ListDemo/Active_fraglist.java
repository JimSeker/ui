package edu.cs4730.ListDemo;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class Active_fraglist extends ListFragment {
    String TAG = "Active_fraglist";
    Context myContext;
	
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
    	
		// Create an array of Strings, that will be put to our ListActivity
		ArrayAdapter<Model> adapter = new InteractiveArrayAdapter(getActivity(),	getModel());
		setListAdapter(adapter);
	}
	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		list.add(new Model("Linux"));
		list.add(new Model("Windows7"));
		list.add(new Model("Suse"));
		list.add(new Model("Eclipse"));
		list.add(new Model("Ubuntu"));
		list.add(new Model("Solaris"));
		list.add(new Model("Android"));
		list.add(new Model("iPhone"));
		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}
	//Note no click listener, because it does not get called even when an list item is clicked.
}
