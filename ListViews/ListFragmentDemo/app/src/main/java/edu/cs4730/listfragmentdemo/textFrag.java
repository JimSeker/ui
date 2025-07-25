package edu.cs4730.listfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.listfragmentdemo.databinding.TextFragmentBinding;

/**
 * This is a simple fragment used to display the data for whichever shakespeare that is clicked on
 * in the titlefrag fragment.  It also shows how to quickly save a small piece of information (position)
 */

public class textFrag extends Fragment {

    int myPosition = 0;
    TextFragmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            myPosition = savedInstanceState.getInt("position");
        }
        binding = TextFragmentBinding.inflate(inflater, container, false);
        setText(myPosition);
        return binding.getRoot();
    }

    /*
     * simple method to set the text of the TextView from the layout, called from the TitleFrag.
     */
    public void setText(int item) {

        binding.text.setText(Shakespeare.DIALOGUE[item]);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current article selection in case we need to recreate the fragment
        outState.putInt("position", myPosition);
    }
}
