package edu.cs4730.dialogdemo_kt

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import edu.cs4730.dialogdemo_kt.databinding.FragmentEditNameDialogBinding

/**
 * This is a "custom" dialog, which has an edittext box and returns
 * the value back via a listener.
 * this also pops up the keyboard for the editext box as well.
 */
class myEditNameDialogFrag : DialogFragment() {
    private var mListener: EditNameDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater =  layoutInflater
        val binding: FragmentEditNameDialogBinding = FragmentEditNameDialogBinding.inflate(inflater)

        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.AppTheme_Dialog
            )
        )
        builder.setView(binding.root).setTitle("Hello")
        builder.setPositiveButton(
            "Done"
        ) { dialog, id ->
            mListener!!.onFinishEditDialog(binding.txtYourName.text.toString())
            dismiss()
        }
            .setCancelable(false) //don't let them cancel this dialog.  ie use the backbutton to get out of it.

        return  builder.create()
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
