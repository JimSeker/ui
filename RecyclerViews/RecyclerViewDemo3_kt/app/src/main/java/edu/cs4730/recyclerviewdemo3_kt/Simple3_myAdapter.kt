package edu.cs4730.recyclerviewdemo3_kt

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edu.cs4730.recyclerviewdemo3_kt.databinding.Simple2RowlayoutBinding
import edu.cs4730.recyclerviewdemo3_kt.databinding.Simple3RowlayoutBinding

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
        val v = Simple3RowlayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        viewHolder.viewBinding.Name.text = entry
        viewHolder.viewBinding.Name.setOnClickListener { v ->
            val tv = v as TextView
            Toast.makeText(mContext, tv.text, Toast.LENGTH_SHORT).show()
        }
        viewHolder.viewBinding.picture.setImageResource(R.drawable.phone)
    } //mContext.getDrawable(country.getImageResourceId(mContext))

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    inner class ViewHolder(var viewBinding: Simple3RowlayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
    }
}