package edu.cs4730.ListDemo;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import edu.cs4730.ListDemo.databinding.PhonebookFragmentBinding;
import edu.cs4730.ListDemo.databinding.PhonebookRowlayoutBinding;

/*
 * From http://code.google.com/p/myandroidwidgets/source/browse/trunk/Phonebook/src/com/abeanie/PhonebookAdapter.java
 * description found at http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
 */

public class Phonebook_myAdapter extends BaseAdapter implements OnClickListener {
    private Context context;
    String TAG = "Phonebook_myAdapter";
    private List<Phonebook_DataModel> listPhonebook;

    public Phonebook_myAdapter(Context context, List<Phonebook_DataModel> listPhonebook) {
        this.context = context;
        this.listPhonebook = listPhonebook;
    }

    @Override
    public int getCount() {
        return listPhonebook.size();
    }

    @Override
    public Object getItem(int position) {
        return listPhonebook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    PhonebookRowlayoutBinding binding;

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Phonebook_DataModel entry = listPhonebook.get(position);

        if (convertView == null) {
            binding = PhonebookRowlayoutBinding.inflate((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
            Log.d(TAG, "convertView is null");
        } else {
            //I'm not 100 percent sure this works.  my testing seems to say it does work.
            Log.d(TAG, "convertView is NOT null");
            binding = PhonebookRowlayoutBinding.bind(convertView);
        }

        binding.tvContact.setText(entry.getName());
        binding.tvMobile.setText(entry.getPhone());
        binding.tvMail.setText(entry.getMail());

        // Set the onClick Listener on this button
        binding.btnRemove.setFocusableInTouchMode(false);
        binding.btnRemove.setFocusable(false);
        binding.btnRemove.setOnClickListener(this);
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item that was clicked.
        // btnRemove.setId(position);
        binding.btnRemove.setTag(entry);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Phonebook_DataModel entry = (Phonebook_DataModel) view.getTag();
        //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
        listPhonebook.remove(entry);
        notifyDataSetChanged();

    }

    private void showDialog(Phonebook_DataModel entry) {
        // Create and show your dialog
        // Depending on the Dialogs button clicks delete it or do nothing
    }

}

