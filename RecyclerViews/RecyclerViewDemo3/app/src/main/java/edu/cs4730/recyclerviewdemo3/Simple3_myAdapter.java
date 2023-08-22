package edu.cs4730.recyclerviewdemo3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo3.databinding.Simple3RowlayoutBinding;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class Simple3_myAdapter extends RecyclerView.Adapter<Simple3_myAdapter.ViewHolder>{

    private List<String> myList;
    private int rowLayout;
    private Context mContext;

    public Simple3_myAdapter (List<String> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Simple3RowlayoutBinding v = Simple3RowlayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList.get(i);
        viewHolder.viewBinding.Name.setText(entry);
        viewHolder.viewBinding.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                Toast.makeText(mContext,tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.viewBinding.picture.setImageResource(R.drawable.phone);
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Simple3RowlayoutBinding viewBinding;
        public ViewHolder(Simple3RowlayoutBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }
    }
}
