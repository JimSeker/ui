package edu.cs4730.navdrawerfragdemo_kt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import edu.cs4730.navdrawerfragdemo_kt.databinding.ListfragmentLayoutBinding

/**
 * this ia listfragment.  All we need to do is setlistadapter in onCreateView (there is no layout)
 * and override onListItemClick.  Since we also have callbacks, also deal with those.
 */
class titlefrag : ListFragment() {
    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private var mListener: OnFragmentInteractionListener? = null
    lateinit var binding: ListfragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ListfragmentLayoutBinding.inflate(inflater, container, false)
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, Shakespeare.TITLES)
        listAdapter = adapter
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity = requireActivity()
        mListener = try {
            activity as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$activity must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onListItemClick(listView: ListView, view: View, position: Int, id: Long) {
        super.onListItemClick(listView, view, position, id)

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        if (mListener != null) mListener!!.onItemSelected(position)
    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    interface OnFragmentInteractionListener {
        /**
         * Callback for when an item has been selected.
         */
        fun onItemSelected(id: Int)
    }
}