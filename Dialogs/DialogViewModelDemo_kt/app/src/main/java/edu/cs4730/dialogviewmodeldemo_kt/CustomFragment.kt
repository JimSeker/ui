package edu.cs4730.dialogviewmodeldemo_kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * This shows how to use the dialog that have been extended.  Note the listeners are implemented
 * in the mainActivity and call the displaylog method at the bottom of this fragment.
 * This may not be the way you want to implements your listeners.  It was easier for communication of
 * the fragments.  Sometimes you want to the listeners om the dialog itself, if you have access to the
 * necessary functions and data.
 */
class CustomFragment : Fragment() {
    var TAG = "CustomFragment"
    lateinit var mViewModel: DataViewModel
    var logger: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.fragment_custom, container, false)
        logger = myView.findViewById(R.id.logger_custom)

        //note, I'm not setting up an observer here, but you could do here instead, but mainactivty "could" change the fragment
        //based on data, so for this example it's in main activity, but honesty doesn't need to be.
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        myView.findViewById<View>(R.id.btn_alert1).setOnClickListener {
            val newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_TYPE_ID)
            newDialog.show(requireActivity().supportFragmentManager, "myDialog")
        }
        myView.findViewById<View>(R.id.btn_alert2).setOnClickListener {
            val newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_GAMEOVER_ID)
            newDialog.show(requireActivity().supportFragmentManager, "myDialog")
        }
        myView.findViewById<View>(R.id.btn_alert3).setOnClickListener {
            val newDialog =
                myAlertDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title)
            newDialog.show(requireActivity().supportFragmentManager, "myAlertDialog")
        }
        myView.findViewById<View>(R.id.btn_edit).setOnClickListener {
            val newDialog = myEditNameDialogFrag()
            newDialog.show(requireActivity().supportFragmentManager, "myEditDialog")
        }
        myView.findViewById<View>(R.id.inline_button).setOnClickListener {
            showInputDialog(
                "Inline Fragment"
            )
        }
        myView.findViewById<View>(R.id.mutli_input_btn).setOnClickListener {
            val newDialog = MultiInputDialogFragment.newInstance("Jim", null)
            newDialog.show(requireActivity().supportFragmentManager, "myMultiInputDialog")
        }
        return myView
    }

    /**
     * simple method to display data to the logger textview.
     */
    fun displaylog(item: String) {
        Log.v(TAG, item)
        if (logger != null) //this is called from the oncreate before logger exists, one time, so got to check..
            logger!!.append(item + "\n")
    }

    /**
     * We are going to add data
     * setup a dialog fragment to ask the user for the new item data or category.
     */
    fun showInputDialog(title: String?) {
        val inflater = LayoutInflater.from(activity)
        val textenter = inflater.inflate(R.layout.layout_custom_dialog, null)
        val userinput = textenter.findViewById<View>(R.id.item_added) as EditText
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                activity, R.style.AppTheme_Dialog  //androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog
            )
        )
        builder.setView(textenter).setTitle(title)
        builder.setPositiveButton(
            "Add"
        ) { dialog, id ->
            mViewModel.setItem1("data is " + userinput.text.toString())
            //Toast.makeText(getBaseContext(), userinput.getText().toString(), Toast.LENGTH_LONG).show();
        }
            .setNegativeButton(
                "Cancel"
            ) { dialog, id ->
                mViewModel.setYesNo(false)
                dialog.cancel()
            }
        //you can create the dialog or just use the now method in the builder.
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.show()
    }
}
