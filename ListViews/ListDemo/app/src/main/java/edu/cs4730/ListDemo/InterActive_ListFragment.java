package edu.cs4730.ListDemo;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class InterActive_ListFragment extends ListFragment {
    String TAG = "InterActive_ListFragment";
    Context myContext;
	
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
    	
		// Create an array of Strings, that will be put to our ListActivity
		ArrayAdapter<InterActive_DataModel> adapter = new InterActive_myArrayAdapter(getActivity(),	getModel());
		setListAdapter(adapter);
	}
	private List<InterActive_DataModel> getModel() {
		List<InterActive_DataModel> list = new ArrayList<InterActive_DataModel>();
		list.add(new InterActive_DataModel("Linux"));
		list.add(new InterActive_DataModel("Windows7"));
		list.add(new InterActive_DataModel("Suse"));
		list.add(new InterActive_DataModel("Eclipse"));
		list.add(new InterActive_DataModel("Ubuntu"));
		list.add(new InterActive_DataModel("Solaris"));
		list.add(new InterActive_DataModel("Android"));
		list.add(new InterActive_DataModel("iPhone"));
		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}
	//Note no click listener, because it does not get called even when an list item is clicked.
}
