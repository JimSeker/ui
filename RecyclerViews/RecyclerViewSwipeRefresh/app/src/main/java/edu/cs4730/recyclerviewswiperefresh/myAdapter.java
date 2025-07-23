package edu.cs4730.recyclerviewswiperefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewswiperefresh.databinding.MyRowBinding;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private String[] values;
    private List<String> myList;
    private Context mContext;
    private Random mRandom = new Random();

    public myAdapter(String[] values, Context context) {
        this.values = values;
        this.mContext = context;
        //now get actual list to display.
        randomlist();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyRowBinding binding = MyRowBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);
        viewHolder.viewBinding.Name.setText(entry);
        viewHolder.viewBinding.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(mContext, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.viewBinding.picture.setImageResource(R.drawable.phone);
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MyRowBinding viewBinding;

        public ViewHolder(MyRowBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }

    //a simple method to create a random list of vlaues.
    public void randomlist() {
        myList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            myList.add(values[(mRandom.nextInt(values.length - 1))]);
        }
    }

    //a simple method to remove an item from the list for swipe left.
    public void removeitem(int i) {
        myList.remove(i);
        notifyDataSetChanged();
    }
}
