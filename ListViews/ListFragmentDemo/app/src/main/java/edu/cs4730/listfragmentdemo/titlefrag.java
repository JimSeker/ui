package edu.cs4730.listfragmentdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import edu.cs4730.listfragmentdemo.databinding.ListfragmentLayoutBinding;

/**
 * This ia listfragment.  All we need to do is setlistadapter in onCreateView (there is no layout)
 * and override onListItemClick.  Since we also have callbacks, also deal with those.
 */

public class titlefrag extends ListFragment {

    /**
     * The fragment's current callback object, which is notified of list item clicks.
     */
    private OnFragmentInteractionListener mListener;
    ListfragmentLayoutBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = ListfragmentLayoutBinding.inflate(inflater, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, Shakespeare.TITLES);
        setListAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = requireActivity();
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        listView.setSelection(position);
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        if (mListener != null)
            mListener.onItemSelected(position);
    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface OnFragmentInteractionListener {
        /**
         * Callback for when an item has been selected.
         */
        void onItemSelected(int id);
    }

}
