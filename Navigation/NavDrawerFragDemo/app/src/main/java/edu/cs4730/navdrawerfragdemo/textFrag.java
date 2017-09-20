package edu.cs4730.navdrawerfragdemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 * This is a simple fragment used to display the data for whichever shakespeare that is clicked on
 * in the titlefrag fragment.  It also shows how to quickly save a small piece of information (position)
 */

public class textFrag extends Fragment {

	int myPosition = 0;
	TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


		// If activity recreated (such as from screen rotate), restore
		// the previous article selection set by onSaveInstanceState().
		// This is primarily necessary when in the two-pane layout.
		if (savedInstanceState != null) {
			myPosition = savedInstanceState.getInt("position");
		}
		View view = inflater.inflate(R.layout.text_fragment, container, false);
		tv = view.findViewById(R.id.text);
		setText(myPosition);
		return view;
	}

	/*
	 * simple method to set the text of the TextView from the layout, called from the TitleFrag.
	 */
	public void setText(int item) {
		tv.setText(Shakespeare.DIALOGUE[item]);
	}
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

		// Save the current article selection in case we need to recreate the fragment
		outState.putInt("position", myPosition);
    }
}
