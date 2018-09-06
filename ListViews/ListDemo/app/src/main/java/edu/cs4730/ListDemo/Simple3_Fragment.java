package edu.cs4730.ListDemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import androidx.fragment.app.Fragment;

public class Simple3_Fragment extends Fragment {
    String TAG = "Simple3_Fragment";
    Context myContext;
    
	ListView list;
	Button up, down;
	
	public Simple3_Fragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.simple3_fragment, container, false);

	        list = (ListView) myView.findViewById(R.id.ListView01);
        list.setClickable(true);
        
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(myContext,
				android.R.layout.simple_list_item_1, values);
		list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
            	String item = list.getAdapter().getItem(position).toString();
            	Toast.makeText(myContext, item + " selected", Toast.LENGTH_LONG).show();
            }
        });
		
		
		up = myView.findViewById(R.id.up);
		up.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// up button, so move the list up
				int i = list.getSelectedItemPosition();
				//Toast.makeText(simple3.this, "up selected is " + i,Toast.LENGTH_LONG).show();
				if (i == ListView.INVALID_POSITION) { // nothing selected, so select first position.
					list.setSelection(0);
				}
				if (i > 0) {
					list.setSelection(i - 1);
				}
			}

		});

		down = myView.findViewById(R.id.down);
		down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// down button, so move the it down
				int i = list.getSelectedItemPosition();
				//Toast.makeText(simple3.this, "down selected is " + i, Toast.LENGTH_LONG).show();
				list.setSelection(list.getCount());
			}

		});

		return myView;
	}


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		myContext = context; //needed for toast.
		Log.d(TAG,"onAttach");
	}

	
    
}
