package edu.cs4730.listdemo_kt

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import edu.cs4730.listdemo_kt.databinding.PhonebookRowlayoutBinding

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
    lateinit var binding: PhonebookRowlayoutBinding
    var TAG = "Phonebook_myAdapter"
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {

        val entry = listPhonebook[position]
        if (convertView == null) {
            binding = PhonebookRowlayoutBinding.inflate(
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            )
            Log.d(TAG, "convertView is null")
        } else {
            //I'm not 100 percent sure this works.  my testing seems to say it does work.
            Log.d(TAG, "convertView is NOT null")
            binding = PhonebookRowlayoutBinding.bind(convertView)
        }
        binding.tvContact.text = entry.name
        binding.tvMobile.text = entry.phone
        binding.tvMail.text = entry.mail

        // Set the onClick Listener on this button

        // Set the onClick Listener on this button
        binding.btnRemove.isFocusableInTouchMode = false
        binding.btnRemove.isFocusable = false
        binding.btnRemove.setOnClickListener(this)
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item that was clicked.
        // btnRemove.setId(position);
        binding.btnRemove.tag = entry
        return binding.root
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

