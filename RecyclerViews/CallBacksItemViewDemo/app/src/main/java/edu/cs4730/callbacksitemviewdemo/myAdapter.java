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
    private int rowLayout;
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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        private final String TAG = "ViewHolder";

        public ViewHolder(View view, final OnItemClickListener mlistener) {
            super(view);
            mName = (TextView) view.findViewById(R.id.name);
            /**
             * this setups a clicklistener on the whole layout.  Be careful with buttons, because
             * this is taking the "click".
             */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (mlistener != null) {
                        Log.v(TAG, "Listener at " + TAG);
                        mlistener.onItemClick(itemView, mName.getTag().toString());
                    }
                }
            });
        }
    }

    //constructor
    public myAdapter(List<String> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v, listener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);

        viewHolder.mName.setText(entry);
        //below sets some data into the "tag" field.  The row id in this case, maybe the _id for the database or
        //id of the rest service etc.  tag is pretty generic, so you can put a lot in here.
        viewHolder.mName.setTag(i);  //sample data to show, this is the row id of the list, which is well the same as position in this case.
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
