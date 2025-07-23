package edu.cs4730.recyclerviewdemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo2.databinding.PhoneRowBinding;


/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class phoneAdapter extends RecyclerView.Adapter<phoneAdapter.ViewHolder> {
    private List<Phonebook> myList;
    private Context mContext;

    //viewbinding now provides the references.
    static class ViewHolder extends RecyclerView.ViewHolder {
        public PhoneRowBinding viewBinding;

        ViewHolder(PhoneRowBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }
    }

    //constructor
    phoneAdapter(List<Phonebook> myList, Context context) {
        this.myList = myList;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PhoneRowBinding v = PhoneRowBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Phonebook entry = myList.get(i);
        //here is here the data is set, no variables are declared here.
        viewHolder.viewBinding.tvContact.setText(entry.getName());
        viewHolder.viewBinding.tvMobile.setText(entry.getPhone());
        viewHolder.viewBinding.tvMail.setText(entry.getMail());

        viewHolder.viewBinding.btnRemove.setFocusableInTouchMode(false);
        viewHolder.viewBinding.btnRemove.setFocusable(false);
        viewHolder.viewBinding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phonebook entry = (Phonebook) v.getTag();
                //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
                myList.remove(entry);
                notifyDataSetChanged();
            }
        });

        viewHolder.viewBinding.btnRemove.setTag(entry);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }


}
