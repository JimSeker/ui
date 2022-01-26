package edu.cs4730.dialogviewmodeldemo_kt

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

class myDialogFragment : DialogFragment() {
    /**
     * Instead of using onCreateView, we use the onCreateDialog
     * This uses the ID above to determine which dialog to build.
     *
     *
     * Uses the Viewmodel to send back information.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val id = requireArguments().getInt("id")
        val mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        var dialog: Dialog? = null
        val builder: AlertDialog.Builder
        when (id) {
            DIALOG_TYPE_ID -> {
                val items = arrayOf(
                    "Remove Walls", "Add Walls",
                    "Add/Remove Objects", "Add/Remove Score"
                )
                builder = AlertDialog.Builder(requireActivity())
                builder.setTitle("Choose Type:")
                builder.setSingleChoiceItems(
                    items, -1
                ) { dialog, item ->
                    dialog.dismiss() //the dismiss is needed here or the dialog stays showing.
                    mViewModel.setItem1(items[item])
                }
                dialog = builder.create()
            }
            DIALOG_GAMEOVER_ID -> {
                builder = AlertDialog.Builder(requireActivity())
                builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Yes"
                    ) { dialog, id -> mViewModel.setYesNo(true) }
                    .setNegativeButton(
                        "No"
                    ) { dialog, id -> mViewModel.setYesNo(false) }
                dialog = builder.create()
            }
        }
        return dialog!!
    }

    companion object {
        const val DIALOG_TYPE_ID = 0
        const val DIALOG_GAMEOVER_ID = 1

        /**
         * Create an instance of this fragment.  Uses the "ID numbers" above to determine which
         * dialog box to display.
         */
        fun newInstance(id: Int): myDialogFragment {
            val frag = myDialogFragment()
            val args = Bundle()
            args.putInt("id", id)
            frag.arguments = args
            return frag
        }
    }
}
