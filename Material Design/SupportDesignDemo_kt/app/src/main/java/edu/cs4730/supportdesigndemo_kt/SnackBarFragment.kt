package edu.cs4730.supportdesigndemo_kt

import android.widget.Toast
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * This is an example of how to use the snackbar.  Which is very similar to a toast with
 * two main differences.  first the user can swipe it away and second, you can have a button
 * as well via the setAction call.
 */
class SnackBarFragment : Fragment() {
    lateinit var btn: Button
    lateinit var myView: View
    var SBonClickListener = View.OnClickListener {
        Toast.makeText(
            activity, "You clicked undo", Toast.LENGTH_LONG
        ).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myView = inflater.inflate(R.layout.fragment_snackbar, container, false)
        btn = myView.findViewById(R.id.button01)
        btn.setOnClickListener {
            //This setups and show the snackbar message.
            Snackbar.make(myView, "Hi there?", Snackbar.LENGTH_LONG) //or LENGTH_SHORT
                .setAction("Undo?", SBonClickListener) //this line is optional.
                .show()
        }
        return myView
    }
}