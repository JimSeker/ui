package edu.cs4730.supportdesigndemo4_kt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.cs4730.supportdesigndemo4_kt.databinding.MyRowBinding

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class myAdapter(
    private val myList: List<String>?, private val mContext: Context
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = MyRowBinding.inflate(LayoutInflater.from(viewGroup.context))
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

    // the viewbinding provides the references now.
    inner class ViewHolder(var viewBinding: MyRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {}
}