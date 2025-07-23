package edu.cs4730.recyclerviewdemo2_kt

import android.content.Context
import edu.cs4730.recyclerviewdemo2_kt.Phonebook
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import edu.cs4730.recyclerviewdemo2_kt.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import edu.cs4730.recyclerviewdemo2_kt.databinding.PhoneRowBinding

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class phoneAdapter  //constructor
internal constructor(
    private val myList: MutableList<Phonebook>?,
    private val mContext: Context
) : RecyclerView.Adapter<phoneAdapter.ViewHolder>() {
    //the viewbinding now provides the references.
    class ViewHolder(var viewBinding: PhoneRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {}

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = PhoneRowBinding.inflate(LayoutInflater.from(mContext), viewGroup, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        //here is here the data is set, no variables are declared here.
        viewHolder.viewBinding.tvContact.text = entry.name
        viewHolder.viewBinding.tvMobile.text = entry.phone
        viewHolder.viewBinding.tvMail.text = entry.mail
        viewHolder.viewBinding.btnRemove.isFocusableInTouchMode = false
        viewHolder.viewBinding.btnRemove.isFocusable = false
        viewHolder.viewBinding.btnRemove.setOnClickListener { v ->
            val entry = v.tag as Phonebook
            //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
            myList.remove(entry)
            notifyDataSetChanged()
        }
        viewHolder.viewBinding.btnRemove.tag = entry
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }
}