package edu.cs4730.listviewfragmentdemo_kt

import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * This is a simple fragment used to display the data for whichever shakspeare that is clicked on
 * in the titlefrag fragment.  This is mostly google's code.  It also shows how to quickly save a small
 * piece of information (position) and how to use tuse the getargs as needed.  Except no args are
 * sent to the fragment in this version.  Code is left for reference only.
 */
class textFrag : Fragment() {
    private var myPosition = 0
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            myPosition = requireArguments().getInt(ARG_PARAM1)
        }
    }

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

    /*
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

    companion object {
        private const val ARG_PARAM1 = "param1"
        fun newInstance(param1: Int): textFrag {
            val fragment = textFrag()
            val args = Bundle()
            args.putInt(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}