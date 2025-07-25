package edu.cs4730.listfragmentdemo_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.cs4730.listfragmentdemo_kt.databinding.TextFragmentBinding

/**
 * This is a simple fragment used to display the data for whichever shakespeare that is clicked on
 * in the titlefrag fragment.  It also shows how to quickly save a small piece of information (position)
 */
class textFrag : Fragment() {
    var myPosition = 0
    lateinit var binding: TextFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            myPosition = savedInstanceState.getInt("position")
        }
        binding = TextFragmentBinding.inflate(inflater, container, false)
        setText(myPosition)
        return binding.root
    }

    /**
     * simple method to set the text of the TextView from the layout, called from the TitleFrag.
     */
    fun setText(item: Int) {
        binding.text.text = Shakespeare.DIALOGUE[item]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the current article selection in case we need to recreate the fragment
        outState.putInt("position", myPosition)
    }
}