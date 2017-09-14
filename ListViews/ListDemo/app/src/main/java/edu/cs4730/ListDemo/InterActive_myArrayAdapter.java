package edu.cs4730.ListDemo;
/*  the code taken from below, but has been heavy modified to the point of looking almost
 * nothing like the code on his site.
 *  
 * From http://www.vogella.de/articles/AndroidListView/article.html
 */

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


import android.widget.TextView;

public class InterActive_myArrayAdapter extends ArrayAdapter<InterActive_DataModel> {

    private final List<InterActive_DataModel> list;
    private final Activity context;
    private boolean onBind = false;

    public InterActive_myArrayAdapter(Activity context, List<InterActive_DataModel> list) {
        super(context, R.layout.interactive_rowlayout, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckBox checkbox;
        TextView text;
        if (convertView == null) {
            //having problems with the convertVeiw when not null, so just redoing it each time.  ...
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.interactive_rowlayout, null);
        }
        onBind = true; //we are setting data.
        text = (TextView) convertView.findViewById(R.id.label);
        text.setText(list.get(position).getName());

        checkbox = (CheckBox) convertView.findViewById(R.id.check);
        checkbox.setChecked(list.get(position).isSelected());

        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!onBind) { //if we are nto setting up the data, then do something.  Otherwise, this can cause a loop.
                    //gatTag exists in base class, so no casting is needed here to checkbox.
                    String t = (String) buttonView.getTag();
                    int position = Integer.parseInt(t);
                    Log.w("checkbox", "checkbox has " + t);
                    //now update the model item out of the list, using the position stored in Tag.
                    list.get(position).setSelected(isChecked);
                    Log.w("checkbox", "Position is " + t + " value is " + isChecked);
                    //say we only want one "item" checked and all the other unchecked.
                    if (isChecked) {  //going from unchecked to checked, so update everybody else.
                        for (int i = 0; i < list.size(); i++) {
                            if (i != position)
                                list.get(i).setSelected(false);
                        }
                        notifyDataSetChanged();  //"redraw" any views that were checked.
                    }
                }
            }
        });
        //Tag is an like a temp space, in a widget where you can set some information as an Object Class
        //in this case, the position variable.
        checkbox.setTag(String.valueOf(position));  //used to find the list position when we change the check mark
        onBind = false; //end of setting data.
        return convertView;
    }

}
