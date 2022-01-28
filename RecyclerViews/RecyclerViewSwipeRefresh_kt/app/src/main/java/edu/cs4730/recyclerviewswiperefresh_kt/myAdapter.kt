package edu.cs4730.recyclerviewswiperefresh_kt

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class myAdapter(
    private val values: Array<String>,
    private val rowLayout: Int,
    private val mContext: Context
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    private var myList: MutableList<String>? = null
    private val mRandom = Random()
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.myName.text = entry
        viewHolder.myName.setOnClickListener { v ->
            val tv = v as TextView
            Toast.makeText(mContext, tv.text, Toast.LENGTH_SHORT).show()
        }
        viewHolder.Pic.setImageResource(R.drawable.phone)
    }

    override fun getItemCount(): Int {
        return if (myList == null) 0 else myList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myName: TextView
        var Pic: ImageView

        init {
            myName = itemView.findViewById<View>(R.id.Name) as TextView
            Pic = itemView.findViewById<View>(R.id.picture) as ImageView
        }
    }

    //a simple method to create a random list of vlaues.
    fun randomlist() {
        myList = ArrayList()
        for (i in 0..9) {
            (myList as ArrayList<String>).add(values[mRandom.nextInt(values.size - 1)])
        }
    }

    //a simple method to remove an item from the list for swipe left.
    fun removeitem(i: Int) {
        myList!!.removeAt(i)
        notifyDataSetChanged()
    }

    init {
        //now get actual list to display.
        randomlist()
    }
}