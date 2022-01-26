package edu.cs4730.dialogviewmodeldemo_kt

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

/**
 * this extends a Dialog fragment to create an alterdialog fragment
 */
class myAlertDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = requireArguments().getInt("title")
        val mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        return AlertDialog.Builder(requireActivity()) //.setIcon(R.drawable.alert_dialog_icon)
            .setTitle(title)
            .setPositiveButton(
                R.string.alert_dialog_ok
            ) { dialog, whichButton -> mViewModel.setYesNo(true) }
            .setNegativeButton(
                R.string.alert_dialog_cancel
            ) { dialog, whichButton -> mViewModel.setYesNo(false) }
            .create()
    }

    companion object {
        fun newInstance(title: Int): myAlertDialogFragment {
            val frag = myAlertDialogFragment()
            val args = Bundle()
            args.putInt("title", title)
            frag.arguments = args
            return frag
        }
    }
}
