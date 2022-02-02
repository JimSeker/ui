package edu.cs4730.recyclerviewdemo3;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  needs a comment here.
 */

public class Phonebook_myAdapter extends RecyclerView.Adapter<Phonebook_myAdapter.ViewHolder> implements OnClickListener {
    private Context context;
    private int rowLayout;
    private List<Phonebook_DataModel> listPhonebook;

    public Phonebook_myAdapter(List<Phonebook_DataModel> listPhonebook, int rowLayout, Context context) {
        this.context = context;
        this.listPhonebook = listPhonebook;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        //find our place in the datamodel and recycler view.
        Phonebook_DataModel entry = listPhonebook.get(position);
        //setup the view with data.
        viewHolder.tvContact.setText(entry.getName());
        viewHolder.tvPhone.setText(entry.getPhone());
        viewHolder.tvMail.setText(entry.getMail());

        // Set the onClick Listener on this button
        viewHolder.btnRemove.setOnClickListener(this);
        // Set the entry, so that you can capture which item was clicked and
        // then remove it
        // As an alternative, you can use the id/position of the item to capture
        // the item that was clicked.
        // btnRemove.setId(position);
        viewHolder.btnRemove.setTag(entry);

    }

    @Override
    public int getItemCount() {
        return listPhonebook == null ? 0 : listPhonebook.size();
    }

    /*
     * setup the ViewHolder class with the widget variables, to be used in onBindViewholder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContact;
        TextView tvPhone;
        TextView tvMail;
        Button btnRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContact = (TextView) itemView.findViewById(R.id.tvContact);
            tvPhone = (TextView) itemView.findViewById(R.id.tvMobile);
            tvMail = (TextView) itemView.findViewById(R.id.tvMail);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            btnRemove.setFocusableInTouchMode(false);
            btnRemove.setFocusable(false);
        }
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

