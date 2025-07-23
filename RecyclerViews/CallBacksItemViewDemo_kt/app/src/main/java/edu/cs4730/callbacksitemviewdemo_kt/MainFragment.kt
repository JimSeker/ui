package edu.cs4730.callbacksitemviewdemo_kt

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.cs4730.callbacksitemviewdemo_kt.databinding.FragmentMainBinding
import java.lang.ClassCastException


/**
 * A simple fragment that displays a recyclerview and has a callback
 * plus creates the listener needed in the adapter.
 */
class MainFragment : Fragment() {
    private var mCallback: OntransInteractionCallback? = null
    private lateinit var binding: FragmentMainBinding
    private lateinit var mAdapter: myAdapter
    private val TAG = "MainFragment"
    private val mList: List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.myRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.myRecyclerView.itemAnimator = DefaultItemAnimator()
        mAdapter = myAdapter(mList, requireActivity())

        mAdapter.setOnItemClickListener(object : myAdapter.OnItemClickListener {
            override fun onItemClick(itemView: View, id: String) {
                // String name = users.get(position).name;
                Log.v(TAG, "Listener at $TAG")
                Toast.makeText(context, "MainFragment: $id was clicked!", Toast.LENGTH_SHORT).show()
                //we could just send the id or in this case get the name as something more useful here.
                val name = mAdapter.myList?.get(id.toInt())
                if (name != null) {
                    mCallback!!.ontransInteraction(name)
                } else {
                    //this else should never happen, but kotlin got weird about a null check you don't do in java.
                    mCallback!!.ontransInteraction(id)
                }
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
            requireActivity() as OntransInteractionCallback
        } catch (e: ClassCastException) {
            throw ClassCastException(
                requireActivity()
                    .toString() + " must implement OnFragmentInteractionListener"
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