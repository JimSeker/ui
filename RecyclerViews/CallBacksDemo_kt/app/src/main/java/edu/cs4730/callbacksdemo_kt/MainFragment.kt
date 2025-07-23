package edu.cs4730.callbacksdemo_kt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.cs4730.callbacksdemo_kt.databinding.FragmentMainBinding
import java.util.*

/**
 * A simple fragment that displays a recyclerview and has a callback
 * plus creates the listener needed in the adapter.
 */
class MainFragment : Fragment() {
    private var mCallback: OntransInteractionCallback? = null
    lateinit var binding: FragmentMainBinding
    lateinit var mAdapter: myAdapter
    var TAG = "MainFragment"
    private val mList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.myRecyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter = myAdapter(mList, requireActivity())
        mAdapter.setOnItemClickListener(object : myAdapter.OnItemClickListener {
            override fun onItemClick(itemView: View, id: String) {
                // String name = users.get(position).name;
                Log.v(TAG, "Listener at $TAG")
                Toast.makeText(
                    requireContext(),
                    "MainFragment: $id was clicked!",
                    Toast.LENGTH_SHORT
                ).show()
                mCallback!!.ontransInteraction(id)
            }
        })
        //add the adapter to the recyclerview
        binding.myRecyclerView.adapter = mAdapter
        return binding.root
    }

    //use this one instead of the one above.  Note it's the parameter, context instead of activity.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = try {
            activity as OntransInteractionCallback?
        } catch (e: ClassCastException) {
            throw ClassCastException(
                requireActivity().toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mCallback = null
    }

    //The interface for the call back code that needs to be implemented.
    interface OntransInteractionCallback {
        fun ontransInteraction(item: String)
    }

    init {
        mList = listOf(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )
    }
}
