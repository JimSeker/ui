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

public class InteractiveArrayAdapter extends ArrayAdapter<Model> {

	private final List<Model> list;
	private final Activity context;

	public InteractiveArrayAdapter(Activity context, List<Model> list) {
		super(context, R.layout.rowbuttonlayout, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CheckBox checkbox;
		TextView text;
		//if (convertView == null) {
		//having problems with the convertVeiw when not null, so just redoing it each time.  ...
			LayoutInflater inflator = context.getLayoutInflater();
			convertView = inflator.inflate(R.layout.rowbuttonlayout, null);
			text = (TextView) convertView.findViewById(R.id.label);
			checkbox = (CheckBox) convertView.findViewById(R.id.check);
			
			//Tag is an like a temp space, in a widget where you can set some information as an Object Class
			//in this case, the position variable.
			checkbox.setTag(String.valueOf(position));  //used to find the list position when we change the check mark
			
			checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
				@Override
				public void onCheckedChanged(CompoundButton buttonView,	boolean isChecked) {
					CheckBox cb = (CheckBox)buttonView;
					Log.w("checkbox", "checkbox has "+cb.getTag());
					  //first get the model item out of the list, using the position stored in Tag.
					Model temp = list.get( Integer.parseInt((String)cb.getTag()));
					//now update our Model with the correct information.
					temp.setSelected(cb.isChecked());
					cb.setChecked(temp.isSelected());  //Not necessary since the GUI handles it.
					
					//say we only want one "item" checked and all the other unchecked.
					String t = (String) cb.getTag();
					int position = Integer.parseInt(t);
					Log.w("checkbox", "Position is " + t + " value is " + cb.isChecked());
					for (int i=0; i<list.size();i++) {
						if (i!= position)
						 list.get(i).setSelected(false);
					}
					notifyDataSetChanged();  //"redraw" any views that were checked.
				}	
			});
			
		checkbox.setChecked(list.get(position).isSelected());
		text.setText(list.get(position).getName());
		return convertView;
	}


}
