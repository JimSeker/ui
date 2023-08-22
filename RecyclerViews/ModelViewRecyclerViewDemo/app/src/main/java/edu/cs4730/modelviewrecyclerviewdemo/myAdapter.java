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

import edu.cs4730.modelviewrecyclerviewdemo.databinding.RowLayoutBinding;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 * <p>
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
        RowLayoutBinding viewBinding;

        public ViewHolder(RowLayoutBinding itemview) {
            super(itemview.getRoot());
            viewBinding = itemview;
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
        mViewModel = new ViewModelProvider(mFragment.requireActivity()).get(DataViewModel.class);
        //likely this should be passed the adapter instead.  but it does work.
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RowLayoutBinding v = RowLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);

        viewHolder.viewBinding.name.setText(entry);
        viewHolder.viewBinding.name.setTag(i);  //sample data to show.
        viewHolder.viewBinding.myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers the observer else where.
                Log.wtf(TAG, "setting! " +viewHolder.viewBinding.name.getTag().toString());
                mViewModel.setItem(viewHolder.viewBinding.name.getTag().toString());
                //using the tag, which is set to the index, you can get the data from the list.
                //but note, this case is very simple, so all the data is just in the viewBinding too.
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
