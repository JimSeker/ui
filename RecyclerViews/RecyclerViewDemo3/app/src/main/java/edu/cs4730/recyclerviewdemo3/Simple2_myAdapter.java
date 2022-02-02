package edu.cs4730.recyclerviewdemo3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class Simple2_myAdapter extends RecyclerView.Adapter<Simple2_myAdapter.ViewHolder> {

    private String[] myList;
    private int rowLayout;
    private Context mContext;

    public Simple2_myAdapter(String[] myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList[i];
        viewHolder.myLabel.setText(entry);
        viewHolder.myLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(mContext, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myLabel;
        public ImageView Pic;

        public ViewHolder(View itemView) {
            super(itemView);
            myLabel = (TextView) itemView.findViewById(R.id.label);
        }
    }
}
