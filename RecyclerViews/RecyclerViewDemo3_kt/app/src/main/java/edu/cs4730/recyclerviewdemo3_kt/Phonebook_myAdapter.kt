package edu.cs4730.recyclerviewdemo3_kt

import android.content.Context
import edu.cs4730.recyclerviewdemo3_kt.Phonebook_DataModel
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import edu.cs4730.recyclerviewdemo3_kt.R

/**
 * needs a comment here.
 */
class Phonebook_myAdapter(
    private val listPhonebook: MutableList<Phonebook_DataModel>?,
    private val rowLayout: Int,
    private val context: Context
) : RecyclerView.Adapter<Phonebook_myAdapter.ViewHolder>(), View.OnClickListener {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //find our place in the datamodel and recycler view.
        val entry = listPhonebook!![position]
        //setup the view with data.
        viewHolder.tvContact.text = entry.name
        viewHolder.tvPhone.text = entry.phone
        viewHolder.tvMail.text = entry.mail

        // Set the onClick Listener on this button
        viewHolder.btnRemove.setOnClickListener(this)
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item that was clicked.
        // btnRemove.setId(position);
        viewHolder.btnRemove.tag = entry
    }

    override fun getItemCount(): Int {
        return listPhonebook?.size ?: 0
    }

    /*
     * setup the ViewHolder class with the widget variables, to be used in onBindViewholder
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvContact: TextView
        var tvPhone: TextView
        var tvMail: TextView
        var btnRemove: Button

        init {
            tvContact = itemView.findViewById<View>(R.id.tvContact) as TextView
            tvPhone = itemView.findViewById<View>(R.id.tvMobile) as TextView
            tvMail = itemView.findViewById<View>(R.id.tvMail) as TextView
            btnRemove = itemView.findViewById<View>(R.id.btnRemove) as Button
            btnRemove.isFocusableInTouchMode = false
            btnRemove.isFocusable = false
        }
    }

    override fun onClick(view: View) {
        val entry = view.tag as Phonebook_DataModel
        //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
        listPhonebook!!.remove(entry)
        notifyDataSetChanged()
    }

    private fun showDialog(entry: Phonebook_DataModel) {
        // Create and show your dialog
        // Depending on the Dialogs button clicks delete it or do nothing
    }
}