package edu.cs4730.listdemo_kt

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import edu.cs4730.listdemo_kt.databinding.InteractiveRowlayoutBinding

/*  the code taken from below, but has been heavy modified to the point of looking almost
 * nothing like the code on his site.
 *
 * From http://www.vogella.de/articles/AndroidListView/article.html
 */

class InterActive_myArrayAdapter(
    private val context: Activity,
    private val list: List<InterActive_DataModel>
) :
    ArrayAdapter<InterActive_DataModel?>(context, R.layout.interactive_rowlayout, list) {
    private var onBind = false
    lateinit var binding: InteractiveRowlayoutBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if (convertView == null) {
            binding = InteractiveRowlayoutBinding.inflate(context.layoutInflater)
        } else {
            binding = InteractiveRowlayoutBinding.bind(convertView)
        }

        onBind = true //we are setting data.
        binding.label.text = list[position].name

        binding.check.isChecked = list[position].isSelected
        binding.check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!onBind) { //if we are nto setting up the data, then do something.  Otherwise, this can cause a loop.
                //gatTag exists in base class, so no casting is needed here to checkbox.
                val t = buttonView.tag as String
                val position = t.toInt()
                Log.w("checkbox", "checkbox has $t")
                //now update the model item out of the list, using the position stored in Tag.
                list[position].isSelected = isChecked
                Log.w("checkbox", "Position is $t value is $isChecked")
                //say we only want one "item" checked and all the other unchecked.
                if (isChecked) {  //going from unchecked to checked, so update everybody else.
                    for (i in list.indices) {
                        if (i != position) list[i].isSelected = false
                    }
                    notifyDataSetChanged() //"redraw" any views that were checked.
                }
            }
        }
        //Tag is an like a temp space, in a widget where you can set some information as an Object Class
        //in this case, the position variable.
        binding.check.tag = position.toString() //used to find the list position when we change the check mark
        onBind = false //end of setting data.
        return binding.root
    }
}
