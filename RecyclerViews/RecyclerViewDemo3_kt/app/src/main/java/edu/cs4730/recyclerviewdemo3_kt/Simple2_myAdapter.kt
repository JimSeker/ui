package edu.cs4730.recyclerviewdemo3_kt

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edu.cs4730.recyclerviewdemo3_kt.R

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class Simple2_myAdapter(
    private val myList: Array<String>?,
    private val rowLayout: Int,
    private val mContext: Context
) : RecyclerView.Adapter<Simple2_myAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.myLabel.text = entry
        viewHolder.myLabel.setOnClickListener { v ->
            val tv = v as TextView
            Toast.makeText(mContext, tv.text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myLabel: TextView
        var Pic: ImageView? = null

        init {
            myLabel = itemView.findViewById<View>(R.id.label) as TextView
        }
    }
}