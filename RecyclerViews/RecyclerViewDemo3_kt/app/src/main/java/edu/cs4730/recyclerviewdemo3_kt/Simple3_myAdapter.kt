package edu.cs4730.recyclerviewdemo3_kt

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class Simple3_myAdapter(
    private val myList: List<String>?,
    private val rowLayout: Int,
    private val mContext: Context
) : RecyclerView.Adapter<Simple3_myAdapter.ViewHolder>() {
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
    } //mContext.getDrawable(country.getImageResourceId(mContext))

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myName: TextView
        var Pic: ImageView

        init {
            myName = itemView.findViewById<View>(R.id.Name) as TextView
            Pic = itemView.findViewById<View>(R.id.picture) as ImageView
        }
    }
}