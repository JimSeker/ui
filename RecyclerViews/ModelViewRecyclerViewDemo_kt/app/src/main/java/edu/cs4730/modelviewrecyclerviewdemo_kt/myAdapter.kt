package edu.cs4730.modelviewrecyclerviewdemo_kt

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.modelviewrecyclerviewdemo_kt.databinding.RowLayoutBinding

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 *
 * This code has a ViewModel/LiveData so that the click can be observed by the Fragment and mainActivity.
 * Since the viewmodel can't be static, it is passed the viewholder.
 * likely we should pass the viewmodel to the adapter instead of the fragment, but this example is showing
 * how to get an instance all the three levels (activity, fragment, and adapter).
 */
class myAdapter(
    private val myList: List<String>, private val rowLayout: Int, mFragment: Fragment
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    private val TAG = "myAdapter"
    private val mViewModel: DataViewModel

    //the viewbinding now provides the references.
    class ViewHolder(var viewBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = RowLayoutBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList[i]
        viewHolder.viewBinding.name.text = entry
        viewHolder.viewBinding.name.tag = i //sample data to show.
        viewHolder.viewBinding.myButton.setOnClickListener { // Triggers the observer else where.
            Log.wtf(TAG, "setting! " + viewHolder.viewBinding.name.tag.toString());
            mViewModel.setItem(viewHolder.viewBinding.name.tag.toString())
            //using the tag, which is set to the index, you can get the data from the list.
            //but note, this case is very simple, so all the data is just in the viewBinding too.
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myList.size ?: 0
    }

    //constructor
    init {
        //interesting.  while fragment will work, you need the activity, otherwise this viewmodel is not connected to the activity.
        // key doesn't help in the get method.
        //needed the activity, Doc's Creates a ViewModelProvider, which retains ViewModels while a scope of given Activity is alive.
        mViewModel = ViewModelProvider(mFragment.requireActivity()).get(DataViewModel::class.java)
        //likely the viewmodel should be passed the adapter instead.  but it does work.
    }
}