package edu.cs4730.dialogdemo_kt

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import java.lang.ClassCastException

/**
 * This is a "custom" dialog, which has an edittext box and returns
 * the value back via a listener.
 * this also pops up the keyboard for the editext box as well.
 */
class myEditNameDialogFrag : DialogFragment() {
    private var mListener: EditNameDialogListener? = null
    lateinit var mEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireActivity())
        val myView: View = inflater.inflate(R.layout.fragment_my_edit_name_dialog, null)
        mEditText = myView.findViewById<View>(R.id.txt_your_name) as EditText
        mEditText.requestFocus()
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.ThemeOverlay_AppCompat_Dialog
            )
        )
        builder.setView(myView).setTitle("Hello")
        builder.setPositiveButton(
            "Done"
        ) { dialog, id ->
            mListener!!.onFinishEditDialog(mEditText.text.toString())
            dismiss()
        }
            .setCancelable(false) //don't let them cancel this dialog.  ie use the backbutton to get out of it.
        val dialog: Dialog = builder.create()
        //I want the keyboard to popup, with the dialog, since the edittext has focus.
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return dialog
    }

    //all the callback stuff.
    interface EditNameDialogListener {
        fun onFinishEditDialog(inputText: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            requireActivity() as EditNameDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                requireActivity().toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
}
