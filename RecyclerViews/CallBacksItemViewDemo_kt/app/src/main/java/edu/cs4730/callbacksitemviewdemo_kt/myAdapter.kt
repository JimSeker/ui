package edu.cs4730.callbacksitemviewdemo_kt

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.cs4730.callbacksitemviewdemo_kt.databinding.RowLayoutBinding

/**
 *
 * This code has a listeners defined in the class and ViewHolder class, so that a piece of data
 * can be passed all the way back to the mainActivity (via the fragment)
 *
 * Instead of needing a button, we can use the itemview and set a listener for it.  Note,
 * we didn't make the layout clickable or focusable either.  see
 * itemView.setOnClickListener(new View.OnClickListener() { in the viewvholder.
 */
class myAdapter     //constructor
    (
    var myList: List<String>?,
    private val mContext: Context
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    private val TAG = "myAdapter"

    // Define listener member variable
    private var listener: OnItemClickListener? = null

    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(itemView: View, id: String)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    //the viewbinding now provides the references.
    class ViewHolder(var viewBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = RowLayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.viewBinding.name.text = entry
        //below sets some data into the "tag" field.  The row id in this case, maybe the _id for the database or
        //id of the rest service etc.  tag is pretty generic, so you can put a lot in here.
        viewHolder.viewBinding.name.tag = i

        /**
         * this setups a clicklistener on the whole layout.  Be careful with buttons, because
         * this is taking the "click".
         */
        viewHolder.itemView.setOnClickListener { v ->
            // Triggers click upwards to the adapter on click
            if (listener != null) {
                Log.v(TAG, "Listener at $TAG")
                listener!!.onItemClick(v, viewHolder.viewBinding.name.tag.toString())
                //we have the index for the array.  again a simple example where all the data is view too.
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return if (myList == null) 0 else myList!!.size
    }
}