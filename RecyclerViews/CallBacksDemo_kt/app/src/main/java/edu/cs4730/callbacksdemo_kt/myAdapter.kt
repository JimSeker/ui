package edu.cs4730.callbacksdemo_kt

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button

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
    private lateinit var listener: OnItemClickListener

    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(itemView: View, id: String)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(view: View, mlistener: myAdapter.OnItemClickListener) : RecyclerView.ViewHolder(view) {
        var mName: TextView
        var mButton: Button
        private val TAG = "ViewHolder"

        init {
            mName = view.findViewById<View>(R.id.name) as TextView
            mButton = view.findViewById<View>(R.id.myButton) as Button
            //use itemView instead of button, if you want a click listener for the whole layout.
            //itemView.setOnClickListener(new View.OnClickListener() {
            // Setup the click listener for the button
            mButton.setOnClickListener { // Triggers click upwards to the adapter on click
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
        viewHolder.mName.tag = i //sample data to show.
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

}