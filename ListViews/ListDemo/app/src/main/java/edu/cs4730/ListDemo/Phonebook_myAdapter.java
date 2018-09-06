package edu.cs4730.ListDemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/*
 * From http://code.google.com/p/myandroidwidgets/source/browse/trunk/Phonebook/src/com/abeanie/PhonebookAdapter.java
 * description found at http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
 */

public class Phonebook_myAdapter extends BaseAdapter implements OnClickListener {
    private Context context;

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
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Phonebook_DataModel entry = listPhonebook.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.phonebook_rowlayout, null);
        }
        TextView tvContact = (TextView) convertView.findViewById(R.id.tvContact);
        tvContact.setText(entry.getName());

        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvMobile);
        tvPhone.setText(entry.getPhone());

        TextView tvMail = (TextView) convertView.findViewById(R.id.tvMail);
        tvMail.setText(entry.getMail());

        // Set the onClick Listener on this button
        Button btnRemove = (Button) convertView.findViewById(R.id.btnRemove);
        btnRemove.setFocusableInTouchMode(false);
        btnRemove.setFocusable(false);
        btnRemove.setOnClickListener(this);
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item that was clicked.
        // btnRemove.setId(position);

        btnRemove.setTag(entry);

        

        return convertView;
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

