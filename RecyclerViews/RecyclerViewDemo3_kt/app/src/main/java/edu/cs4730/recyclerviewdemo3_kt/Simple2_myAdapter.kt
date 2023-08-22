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
import edu.cs4730.recyclerviewdemo3_kt.databinding.Simple2RowlayoutBinding

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
        val v = Simple2RowlayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.viewBinding.label.text = entry
        viewHolder.viewBinding.label.setOnClickListener { v ->
            val tv = v as TextView
            Toast.makeText(mContext, tv.text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    inner class ViewHolder(var viewBinding: Simple2RowlayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
    }
}