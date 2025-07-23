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

import edu.cs4730.recyclerviewdemo3.databinding.Simple2RowlayoutBinding;

/*
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class Simple2_myAdapter extends RecyclerView.Adapter<Simple2_myAdapter.ViewHolder> {

    private String[] myList;
    private Context mContext;

    public Simple2_myAdapter(String[] myList, Context context) {
        this.myList = myList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Simple2RowlayoutBinding v = Simple2RowlayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String entry = myList[i];
        viewHolder.viewBinding.label.setText(entry);
        viewHolder.viewBinding.label.setOnClickListener(new View.OnClickListener() {
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
        public Simple2RowlayoutBinding viewBinding;

        public ViewHolder(Simple2RowlayoutBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }
    }
}
