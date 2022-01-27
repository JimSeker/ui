package edu.cs4730.ListDemo;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.ListFragment;

public class InterActive_ListFragment extends ListFragment {
    String TAG = "InterActive_ListFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.listfragment_layout, container, false);
		ArrayAdapter<InterActive_DataModel> adapter = new InterActive_myArrayAdapter(requireActivity(),	getModel());
		setListAdapter(adapter);
		return view;
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

}
