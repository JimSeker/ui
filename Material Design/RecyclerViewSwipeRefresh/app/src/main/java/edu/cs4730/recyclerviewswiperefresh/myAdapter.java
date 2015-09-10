package edu.cs4730.recyclerviewswiperefresh;

/**
 * Created by Seker on 1/22/2015.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>{

    private String[] values;
    private List<String> myList;
    private int rowLayout;
    private Context mContext;
    private Random mRandom = new Random();

    public myAdapter(String[] values, int rowLayout, Context context) {
        this.values = values;
        this.rowLayout = rowLayout;
        this.mContext = context;
        //now get actual list to display.
        randomlist();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);
        viewHolder.myName.setText(entry);
        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                Toast.makeText(mContext,tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.Pic.setImageResource(R.drawable.phone);
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myName;
        public ImageView Pic;
        public ViewHolder(View itemView) {
            super(itemView);
            myName = (TextView) itemView.findViewById(R.id.Name);
            Pic= (ImageView)itemView.findViewById(R.id.picture);
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
