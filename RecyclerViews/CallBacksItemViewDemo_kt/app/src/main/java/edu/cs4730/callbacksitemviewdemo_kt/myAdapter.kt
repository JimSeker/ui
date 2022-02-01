package edu.cs4730.callbacksitemviewdemo_kt

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View

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
    private val rowLayout: Int, //for things like a toast or other things that need context.
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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(view: View, mlistener: OnItemClickListener?) : RecyclerView.ViewHolder(view) {
        var mName: TextView
        private val TAG = "ViewHolder"

        init {
            mName = view.findViewById<View>(R.id.name) as TextView
            /**
             * this setups a clicklistener on the whole layout.  Be careful with buttons, because
             * this is taking the "click".
             */
            itemView.setOnClickListener { // Triggers click upwards to the adapter on click
                if (mlistener != null) {
                    Log.v(TAG, "Listener at $TAG")
                    mlistener.onItemClick(itemView, mName.tag.toString())
                }
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
        return ViewHolder(v, listener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.mName.text = entry
        //below sets some data into the "tag" field.  The row id in this case, maybe the _id for the database or
        //id of the rest service etc.  tag is pretty generic, so you can put a lot in here.
        viewHolder.mName.tag =
            i //sample data to show, this is the row id of the list, which is well the same as position in this case.
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return if (myList == null) 0 else myList!!.size
    }
}