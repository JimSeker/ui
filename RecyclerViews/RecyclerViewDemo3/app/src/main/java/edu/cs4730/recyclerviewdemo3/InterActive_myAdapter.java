package edu.cs4730.recyclerviewdemo3;
/*
 *  A simple example of how to make the views interaction with a checkbox and data model.
 */

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.cs4730.recyclerviewdemo3.databinding.InteractiveRowlayoutBinding;


public class InterActive_myAdapter extends RecyclerView.Adapter<InterActive_myAdapter.ViewHolder> {

    private List<InterActive_DataModel> myList;
    private int rowLayout;
    private Context mContext;
    private boolean onBind;

    public InterActive_myAdapter(List<InterActive_DataModel> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        InteractiveRowlayoutBinding v = InteractiveRowlayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        onBind = true;  //this will stop the checkbox listener from doing anything while we are setting up the data.
        viewHolder.viewBinding.label.setText(myList.get(i).getName());
        viewHolder.viewBinding.check.setChecked(myList.get(i).isSelected());
        viewHolder.viewBinding.check.setOnCheckedChangeListener(
            new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!onBind) {  //if we are nto setting up the data, then do something.  Otherwise, this can cause a loop.
                        //get the "tag" out of the checkbox [tag exists base class view], so I know where it is in the myList.
                        String t = (String) buttonView.getTag();
                        int position = Integer.parseInt(t);
                        Log.w("checkbox listener", "checkbox has " + t);
                        //now update the model item out of the list, using the position stored in Tag.
                        myList.get(position).setSelected(isChecked);
                        Log.w("checkbox", "Position is " + t + " value is " + isChecked);
                        if (isChecked) { //going from unchecked to checked, so update everybody else.
                            for (int i = 0; i < myList.size(); i++) {
                                if (i != position)
                                    myList.get(i).setSelected(false);
                            }
                            notifyDataSetChanged();
                        }
                    }
                }
            }

        );
        //Tag is an like a temp space, in a widget where you can set some information as an Object Class
        //in this case, the position variable.
        viewHolder.viewBinding.check.setTag(String.valueOf(i));  //used to find the list position when we change the check mark
        onBind = false;
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

    //viewbinding setups the references for us.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        InteractiveRowlayoutBinding viewBinding;
        public ViewHolder(InteractiveRowlayoutBinding itemView) {
            super(itemView.getRoot());
            viewBinding = itemView;
        }
    }

}
