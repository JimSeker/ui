package edu.cs4730.dialogdemo_kt

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

class myDialogFragment : DialogFragment() {
    private var mListener: OnDialogFragmentListener? = null

    /**
     * Instead of using onCreateView, we use the onCreateDialog
     * This uses the ID above to determine which dialog to build.
     * Note, this calls back into the activity, so the methods
     * doItem(String), doPositiveClick(), and doNegativeClick() need to be implemented. (not call backs either)
     *
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val id = requireArguments().getInt("id")
        lateinit var dialog: Dialog
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
                    mListener!!.doItem(items[item])
                }
                dialog = builder.create()
            }

            DIALOG_GAMEOVER_ID -> {
                builder = AlertDialog.Builder(requireActivity())
                builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Yes"
                    ) { dialog, id -> mListener!!.doPositiveClick() }.setNegativeButton(
                        "No"
                    ) { dialog, id -> mListener!!.doNegativeClick() }
                dialog = builder.create()
            }
        }
        return dialog
    }

    //all the callback stuff.
    interface OnDialogFragmentListener {
        fun doPositiveClick()
        fun doNegativeClick()
        fun doItem(item: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity? = activity
        mListener = try {
            activity as OnDialogFragmentListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    companion object {
        const val DIALOG_TYPE_ID = 0
        const val DIALOG_GAMEOVER_ID = 1

        /*
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
