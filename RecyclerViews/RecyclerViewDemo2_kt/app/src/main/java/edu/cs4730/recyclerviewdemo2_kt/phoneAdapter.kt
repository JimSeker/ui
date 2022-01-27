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

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */
class phoneAdapter  //constructor
internal constructor(
    private val myList: MutableList<Phonebook>?,
    private val rowLayout: Int,
    private val mContext: Context
) : RecyclerView.Adapter<phoneAdapter.ViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContact: TextView
        var tvPhone: TextView
        var tvMail: TextView
        var btnRemove: Button

        //all the declares and findby are here.  onBind is where the data is setup.
        init {
            tvContact = itemView.findViewById(R.id.tvContact)
            tvPhone = itemView.findViewById(R.id.tvMobile)
            tvMail = itemView.findViewById(R.id.tvMail)
            btnRemove = itemView.findViewById(R.id.btnRemove)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val entry = myList!![i]
        //here is here the data is set, no variables are declared here.
        viewHolder.tvContact.text = entry.name
        viewHolder.tvPhone.text = entry.phone
        viewHolder.tvMail.text = entry.mail
        viewHolder.btnRemove.isFocusableInTouchMode = false
        viewHolder.btnRemove.isFocusable = false
        viewHolder.btnRemove.setOnClickListener { v ->
            val entry = v.tag as Phonebook
            //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
            myList.remove(entry)
            notifyDataSetChanged()
        }
        viewHolder.btnRemove.tag = entry
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }
}