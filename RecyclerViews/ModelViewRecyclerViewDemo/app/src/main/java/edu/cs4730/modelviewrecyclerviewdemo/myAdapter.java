package edu.cs4730.modelviewrecyclerviewdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 *
 * This code has a ViewModel/LiveData so that the click can be observed by the Fragment and mainActivity.
 * Since the viewmodel can't be static, it is passed the viewholder.
 * likely we should pass the viewmodel to the adapter instead of the fragment, but this example is showing
 * how to get an instance all the three levels (activity, fragment, and adapter).
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<String> myList;
    private int rowLayout;
    private Fragment mFragment;
    private final String TAG = "myAdapter";
    private DataViewModel mViewModel;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public Button mButton;

        private final String TAG = "ViewHolder";

        public ViewHolder(View view, final DataViewModel mViewModel) {
            super(view);
            mName = view.findViewById(R.id.name);
            mButton = view.findViewById(R.id.myButton);
            //use itemView instead of button, if you want a click listener for the whole layout.
            //itemView.setOnClickListener(new View.OnClickListener() {
            // Setup the click listener for the button
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers the observer else where.
                    Log.wtf(TAG, "setting! " + mName.getTag().toString());
                    mViewModel.setItem(mName.getTag().toString());
                }
            });
        }
    }

    //constructor
    public myAdapter(List<String> myList, int rowLayout, Fragment mFragment) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mFragment = mFragment;
        //interesting.  while fragment will work, you need the activity, otherwise this viewmodel is not connected to the activity.
        // key doesn't help in the get method.
        //needed the activity, Doc's Creates a ViewModelProvider, which retains ViewModels while a scope of given Activity is alive.
        mViewModel = new ViewModelProvider(mFragment.getActivity()).get(DataViewModel.class);
        //likely this should be passed the adapter instead.  but it does work.
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v, mViewModel);
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
