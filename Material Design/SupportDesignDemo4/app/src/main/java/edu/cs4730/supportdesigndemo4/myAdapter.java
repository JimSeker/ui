package edu.cs4730.supportdesigndemo4;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.cs4730.supportdesigndemo4.databinding.MyRowBinding;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<String> myList;
    private int rowLayout;
    private Context mContext;

    public myAdapter(List<String> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyRowBinding binding = MyRowBinding.inflate(LayoutInflater.from(mContext));
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

    //viewbinding provides the references.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        MyRowBinding viewBinding;
        public ViewHolder(MyRowBinding v) {
            super(v.getRoot());
            viewBinding = v;
        }
    }
}
