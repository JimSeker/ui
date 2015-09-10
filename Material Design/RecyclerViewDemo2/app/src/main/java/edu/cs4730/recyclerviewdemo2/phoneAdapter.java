package edu.cs4730.recyclerviewdemo2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Seker on 1/24/2015.
 */
public class phoneAdapter extends RecyclerView.Adapter<phoneAdapter.ViewHolder> {


    private List<Phonebook> myList;
    private int rowLayout;
    private Context mContext;

    public phoneAdapter(List<Phonebook> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Phonebook entry = myList.get(i);
        //here is here the data is set, no variables are declared here.
        viewHolder.tvContact.setText(entry.getName());
        viewHolder.tvPhone.setText(entry.getPhone());
        viewHolder.tvMail.setText(entry.getMail());

        viewHolder.btnRemove.setFocusableInTouchMode(false);
        viewHolder.btnRemove.setFocusable(false);
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phonebook entry = (Phonebook) v.getTag();
                //We could call a dialog showDialog(entry), if we wanted to change it instead of deleting it.
                myList.remove(entry);
                notifyDataSetChanged();
            }
        });

        viewHolder.btnRemove.setTag(entry);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvContact;
        public TextView tvPhone;
        public TextView tvMail;
        public Button btnRemove;
        //all the declares and findby are here.  onBind is where the data is setup.
        public ViewHolder(View itemView) {
            super(itemView);
            tvContact = (TextView) itemView.findViewById(R.id.tvContact);
            tvPhone = (TextView) itemView.findViewById(R.id.tvMobile);
            tvMail = (TextView) itemView.findViewById(R.id.tvMail);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
        }
    }
}
