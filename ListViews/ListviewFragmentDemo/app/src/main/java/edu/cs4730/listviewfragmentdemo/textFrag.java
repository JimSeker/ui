package edu.cs4730.listviewfragmentdemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 * This is a simple fragment used to display the data for whichever shakspeare that is clicked on
 * in the titlefrag fragment.  This is mostly google's code.  It also shows how to quickly save a small
 * piece of information (position) and how to use tuse the getargs as needed.  Except no args are
 * sent to the fragment in this version.  Code is left for reference only.
 */

public class textFrag extends Fragment {

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_TEXT_ID = "text_id";
	
	
	int myposition=0;

	
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
            myposition = savedInstanceState.getInt("position");
        }
		View view = inflater.inflate(R.layout.text_fragment, container, false);
		return view;
	}
    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            setText(args.getInt("position"));
        } else {
            // Set article based on saved instance state defined during onCreateView
            setText(myposition);
        }
    }
	/*
	 * simple method to set the text of the TextView from the layout, called from the TitleFrag.
	 */
	public void setText(int item) {
		TextView tv = (TextView) getView().findViewById(R.id.text);
		tv.setText(Shakespeare.DIALOGUE[item]);
	}
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt("position", myposition);
    }
}
