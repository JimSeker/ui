package edu.cs4730.callbacksdemo;

/**
 * Created by Seker on 1/22/2015.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/*
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 *
 * This code has a listeners defined in the class and ViewHolder class, so that a piece of data
 * can be passed all the way back to the mainActivity (via the fragment)
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>{

    private List<String> myList;
    private int rowLayout;
    private Context mContext;  //for things like a toast or other things that need context.
    private final String TAG= "myAdapter";


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
        public Button mButton;
        private final String TAG= "ViewHolder";
        public ViewHolder(View view, final OnItemClickListener mlistener) {
            super(view);
            mName = (TextView) view.findViewById(R.id.name);
            mButton = (Button) view.findViewById(R.id.myButton);
            //use itemView instead of button, if you want a click listener for the whole layout.
            //itemView.setOnClickListener(new View.OnClickListener() {
            // Setup the click listener for the button
            mButton.setOnClickListener(new View.OnClickListener() {
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
        viewHolder.mName.setTag(i);  //sample data to show.
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
