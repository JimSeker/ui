package edu.cs4730.callbacksdemo_kt

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.cs4730.callbacksdemo_kt.databinding.RowLayoutBinding

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 *
 *
 * This code has a listeners defined in the class and ViewHolder class, so that a piece of data
 * can be passed all the way back to the mainActivity (via the fragment)
 */
class myAdapter     //constructor
    (
    private val myList: List<String>?,
    private val rowLayout: Int, //for things like a toast or other things that need context.
    private val mContext: Context
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    private val TAG = "myAdapter"

    // Define listener member variable
    private var listener: OnItemClickListener? = null;

    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(itemView: View, id: String)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    //the viewbinding now provides the references.
    class ViewHolder(var viewBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = RowLayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.viewBinding.name.text = entry
        viewHolder.viewBinding.name.tag = i //sample data to show.

        // Setup the click listener for the button
        // Setup the click listener for the button
        viewHolder.viewBinding.myButton.setOnClickListener { v ->
            // Triggers click upwards to the adapter on click
            if (listener != null) {
                Log.v(TAG, "Listener at $TAG")
                listener!!.onItemClick(v, viewHolder.viewBinding.name.tag.toString())
                //this gets you the index to the array, but this example is very simple and
                //all the info is also in the view/viewbinding
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

}