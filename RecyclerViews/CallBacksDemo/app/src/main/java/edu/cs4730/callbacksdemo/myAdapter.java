package edu.cs4730.callbacksdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.callbacksdemo.databinding.RowLayoutBinding;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 * <p>
 * This code has a listeners defined in the class and ViewHolder class, so that a piece of data
 * can be passed all the way back to the mainActivity (via the fragment)
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<String> myList;
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RowLayoutBinding viewBinding;

        public ViewHolder(RowLayoutBinding itemview) {
            super(itemview.getRoot());
            viewBinding = itemview;
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
        RowLayoutBinding v = RowLayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);

        viewHolder.viewBinding.name.setText(entry);
        viewHolder.viewBinding.name.setTag(i);  //sample data to show.
        // Setup the click listener for the button
        viewHolder.viewBinding.myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    Log.v(TAG, "Listener at " + TAG);
                    listener.onItemClick(v, viewHolder.viewBinding.name.getTag().toString());
                    //this gets you index to the array, but this example is very simple and
                    //all the info is also in the view/viewbinding
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
