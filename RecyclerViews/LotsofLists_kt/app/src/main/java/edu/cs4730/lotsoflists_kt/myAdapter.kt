package edu.cs4730.lotsoflists_kt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.cs4730.lotsoflists_kt.databinding.MyRowBinding

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class myAdapter(
    private var myList: List<String>?,
    private val mContext: Context
) : RecyclerView.Adapter<myAdapter.ViewHolder>() {
    //a simple method to make sure the recyclerview knows I have changed the data in the list.
    fun newData(myList: List<String>?) {
        this.myList = myList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = MyRowBinding.inflate(LayoutInflater.from(mContext), viewGroup, false)
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
    }

    override fun getItemCount(): Int {
        return if (myList == null) 0 else myList!!.size
    }

    //the viewbinding now provides the references.
    class ViewHolder(var viewBinding: MyRowBinding) : RecyclerView.ViewHolder(
        viewBinding.root
    )
}