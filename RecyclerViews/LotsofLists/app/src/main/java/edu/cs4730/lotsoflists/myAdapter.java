package edu.cs4730.lotsoflists;

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

import edu.cs4730.lotsoflists.databinding.MyRowBinding;

/**
 * this adapter is very similar to the adapters used for listview, except a ViewHolder is required
 * see http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 * except instead having to implement a ViewHolder, it is implemented within
 * the adapter.
 */

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<String> myList;
    private Context mContext;

    public myAdapter(List<String> myList, Context context) {
        this.myList = myList;
        this.mContext = context;
    }

    //a simple method to make sure the recyclerview knows I have changed the data in the list.
    public void newData(List<String> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyRowBinding v = MyRowBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
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

    //the viewbinding now provides the references.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        MyRowBinding viewBinding;

        public ViewHolder(MyRowBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
