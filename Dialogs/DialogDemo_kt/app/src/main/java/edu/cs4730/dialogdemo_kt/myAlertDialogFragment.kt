package edu.cs4730.dialogdemo_kt

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

/**
 * this extends a Dialog fragment to create an alterdialog fragment
 */
class myAlertDialogFragment : DialogFragment() {
    private var mListener: OnDialogFragmentListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = requireArguments().getInt("title")
        return AlertDialog.Builder(requireActivity()) //.setIcon(R.drawable.alert_dialog_icon)
            .setTitle(title)
            .setPositiveButton(
                R.string.alert_dialog_ok
            ) { dialog, whichButton -> mListener!!.doPositiveClick() }
            .setNegativeButton(
                R.string.alert_dialog_cancel
            ) { dialog, whichButton -> mListener!!.doNegativeClick() }
            .create()
    }

    //all the callback stuff.
    interface OnDialogFragmentListener {
        fun doPositiveClick()
        fun doNegativeClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: Activity? = activity
        mListener = try {
            activity as OnDialogFragmentListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
