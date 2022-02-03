package edu.cs4730.recyclerviewdemo3_kt

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView

/**
 *  A simple example of how to make the views interaction with a checkbox and data model.
 */   class InterActive_myAdapter(
    private val myList: List<InterActive_DataModel>?,
    private val rowLayout: Int,
    private val mContext: Context
) : RecyclerView.Adapter<InterActive_myAdapter.ViewHolder>() {
    private var onBind = false
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(rowLayout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        onBind =
            true //this will stop the checkbox listener from doing anything while we are setting up the data.
        viewHolder.text.text = myList!![i].name
        viewHolder.checkbox.isChecked = myList[i].isSelected
        viewHolder.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!onBind) {  //if we are nto setting up the data, then do something.  Otherwise, this can cause a loop.
                //get the "tag" out of the checkbox [tag exists base class view], so I know where it is in the myList.
                val t = buttonView.tag as String
                val position = t.toInt()
                Log.w("checkbox listener", "checkbox has $t")
                //now update the model item out of the list, using the position stored in Tag.
                myList[position].isSelected = isChecked
                Log.w("checkbox", "Position is $t value is $isChecked")
                if (isChecked) { //going from unchecked to checked, so update everybody else.
                    for (i in myList.indices) {
                        if (i != position) myList[i].isSelected = false
                    }
                    notifyDataSetChanged()
                }
            }
        }
        //Tag is an like a temp space, in a widget where you can set some information as an Object Class
        //in this case, the position variable.
        viewHolder.checkbox.tag =
            i.toString() //used to find the list position when we change the check mark
        onBind = false
    }

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    /*
     * setup the ViewHolder class with the widget variables, to be used in onBindViewholder
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox: CheckBox
        var text: TextView

        init {
            text = itemView.findViewById<View>(R.id.label) as TextView
            checkbox = itemView.findViewById<View>(R.id.check) as CheckBox
        }
    }
}