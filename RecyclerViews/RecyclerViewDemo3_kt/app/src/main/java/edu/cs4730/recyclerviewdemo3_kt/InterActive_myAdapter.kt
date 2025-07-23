package edu.cs4730.recyclerviewdemo3_kt

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import edu.cs4730.recyclerviewdemo3_kt.databinding.InteractiveRowlayoutBinding

/**
 *  A simple example of how to make the views interaction with a checkbox and data model.
 */   class InterActive_myAdapter(
    private val myList: List<InterActive_DataModel>?,
    private val mContext: Context
) : RecyclerView.Adapter<InterActive_myAdapter.ViewHolder>() {
    private var onBind = false
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = InteractiveRowlayoutBinding.inflate(LayoutInflater.from(mContext), viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        onBind =
            true //this will stop the checkbox listener from doing anything while we are setting up the data.
        viewHolder.viewBinding.label.text = myList!![i].name
        viewHolder.viewBinding.check.isChecked = myList[i].isSelected
        viewHolder.viewBinding.check.setOnCheckedChangeListener { buttonView, isChecked ->
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
        viewHolder.viewBinding.check.tag =
            i.toString() //used to find the list position when we change the check mark
        onBind = false
    }

    override fun getItemCount(): Int {
        return myList?.size ?: 0
    }

    ///viewbinding provides the references.
    inner class ViewHolder(var viewBinding: InteractiveRowlayoutBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    }
}