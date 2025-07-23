package edu.cs4730.callbacksitemviewdemo;

/**
 * Created by Seker on 1/22/2015.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.callbacksitemviewdemo.databinding.RowLayoutBinding;

/**
*
 * This code has a listeners defined in the class and ViewHolder class, so that a piece of data
 * can be passed all the way back to the mainActivity (via the fragment)
 *
 * Instead of needing a button, we can use the itemview and set a listener for it.  Note,
 * we didn't make the layout clickable or focusable either.  see
 *  itemView.setOnClickListener(new View.OnClickListener() { in the viewvholder.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    public List<String> myList;
    private Context mContext;  //for things like a toast or other things that need context.
    private final String TAG = "myAdapter";


    // Define listener member variable
    private OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, String id);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //the viewbinding now provides the references.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        RowLayoutBinding viewBinding;
        public ViewHolder(RowLayoutBinding view) {
            super(view.getRoot());
            viewBinding = view;
        }
    }

    //constructor
    public myAdapter(List<String> myList,  Context context) {
        this.myList = myList;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RowLayoutBinding v = RowLayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);

        viewHolder.viewBinding.name.setText(entry);
        //below sets some data into the "tag" field.  The row id in this case, maybe the _id for the database or
        //id of the rest service etc.  tag is pretty generic, so you can put a lot in here.
        viewHolder.viewBinding.name.setTag(i);  //sample data to show, this is the row id of the list, which is well the same as position in this case.
        /**
         * this setups a clicklistener on the whole layout.  Be careful with buttons, because
         * this is taking the "click".
         */
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    Log.v(TAG, "Listener at " + TAG);
                    listener.onItemClick(v, viewHolder.viewBinding.name.getTag().toString());
                    //we have the index for the array.  again a simple example where all the data is view too.
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
