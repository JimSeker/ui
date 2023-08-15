package edu.cs4730.dialogdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import edu.cs4730.dialogdemo_kt.databinding.FragmentSupportDialogBinding

/**
 * Shows how to build and call a support alert dialog and set the listeners for it.
 * Also shows a list dialog and listeners as well.
 */
class SupportDialogFragment : Fragment() {

    var TAG = "SuppportDialogFragment"
    lateinit var binding: FragmentSupportDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSupportDialogBinding.inflate(inflater, container, false)
        binding.btnSupportAlertDialog.setOnClickListener {
            showdialog("Demo Dialog")
        }
        binding.btnSupportListDialog.setOnClickListener {
            showlistdialog(
                "Demo List Dialog"
            )
        }
        return binding.root
    }

    fun displaylog(item: String) {
        Log.v(TAG, item)
        binding.loggerSupport.append(item + "\n")
    }

    /**
     *  The method is not necessary.
     *
     *  This builds an alert dialog, with the positive button set to Yes, Negative button set to NO.
     *  There is a listener if the dialog is canceled (ie the back button is used)
     */
    fun showdialog(title: String?) {
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.ThemeOverlay_AppCompat_Dialog
            )
        )
        builder.setTitle(title)
        builder.setMessage("Play again?")
        //Button Button
        builder.setPositiveButton(
            "Yes"
        ) { dialog, which -> displaylog("play again") }
        //Negative button
        builder.setNegativeButton(
            "No"
        ) { dialog, which -> displaylog("exit") }
        //If the user uses the back button instead.
        builder.setOnCancelListener { displaylog("canceled ") }
        builder.show()
    }

    /**
     * This shows a list and the use is to select one of them.
     * Note, this dialog doesn't set a cancel listener, like the one above.  so if the user
     * cancels, nothing happens.
     */
    fun showlistdialog(title: String) {
        val items = arrayOf(
            "Remove Walls", "Add Walls",
            "Add/Remove Objects", "Add/Remove Score"
        )
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.AppTheme_Dialog
            )
        )
        builder.setTitle("Choose Type:")
        builder.setSingleChoiceItems(
            items, -1
        ) { dialog, item ->
            dialog.dismiss() //the dismiss is needed here or the dialog stays showing.
            displaylog(items[item])
        }
        builder.show()
    }
}
