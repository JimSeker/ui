package edu.cs4730.listfragmentdemo_kt

import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * This is a simple fragment used to display the data for whichever shakespeare that is clicked on
 * in the titlefrag fragment.  It also shows how to quickly save a small piece of information (position)
 */
class textFrag : Fragment() {
    var myPosition = 0
    lateinit var tv: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            myPosition = savedInstanceState.getInt("position")
        }
        val view = inflater.inflate(R.layout.text_fragment, container, false)
        tv = view.findViewById(R.id.text)
        setText(myPosition)
        return view
    }

    /**
     * simple method to set the text of the TextView from the layout, called from the TitleFrag.
     */
    fun setText(item: Int) {
        tv.text = Shakespeare.DIALOGUE[item]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt("position", myPosition)
    }
}