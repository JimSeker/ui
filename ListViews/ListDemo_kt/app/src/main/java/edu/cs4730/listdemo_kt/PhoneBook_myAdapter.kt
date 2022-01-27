package edu.cs4730.listdemo_kt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView


/*
 * From http://code.google.com/p/myandroidwidgets/source/browse/trunk/Phonebook/src/com/abeanie/PhonebookAdapter.java
 * description found at http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
 */

/*
 * From http://code.google.com/p/myandroidwidgets/source/browse/trunk/Phonebook/src/com/abeanie/PhonebookAdapter.java
 * description found at http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
 */
class Phonebook_myAdapter(
    private val context: Context,
    private val listPhonebook: MutableList<Phonebook_DataModel>
) :
    BaseAdapter(), View.OnClickListener {
    override fun getCount(): Int {
        return listPhonebook.size
    }

    override fun getItem(position: Int): Any {
        return listPhonebook[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        var convertView = convertView
        val entry = listPhonebook[position]
        if (convertView == null) {
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.phonebook_rowlayout, null)
        }
        val tvContact = convertView!!.findViewById<View>(R.id.tvContact) as TextView
        tvContact.text = entry.name
        val tvPhone = convertView!!.findViewById<View>(R.id.tvMobile) as TextView
        tvPhone.text = entry.phone
        val tvMail = convertView!!.findViewById<View>(R.id.tvMail) as TextView
        tvMail.text = entry.mail

        // Set the onClick Listener on this button
        val btnRemove = convertView.findViewById<View>(R.id.btnRemove) as Button
        btnRemove.isFocusableInTouchMode = false
        btnRemove.isFocusable = false
        btnRemove.setOnClickListener(this)
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item that was clicked.
        // btnRemove.setId(position);
        btnRemove.tag = entry
        return convertView
    }

    override fun onClick(view: View) {
        val entry = view.tag as Phonebook_DataModel
        //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
        listPhonebook.remove(entry)
        notifyDataSetChanged()
    }

    private fun showDialog(entry: Phonebook_DataModel) {
        // Create and show your dialog
        // Depending on the Dialogs button clicks delete it or do nothing
    }
}

