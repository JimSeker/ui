package edu.cs4730.listviewfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * This is a simple fragment used to display the data for whichever shakspeare that is clicked on
 * in the titlefrag fragment.  This is mostly google's code.  It also shows how to quickly save a small
 * piece of information (position) and how to use tuse the getargs as needed.  Except no args are
 * sent to the fragment in this version.  Code is left for reference only.
 */

public class textFrag extends Fragment {

    private int myPosition = 0;
    private static final String ARG_PARAM1 = "param1";
    TextView tv;

    public static textFrag newInstance(int param1) {
        textFrag fragment = new textFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myPosition = requireArguments().getInt(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt("position", myPosition);
    }
}
