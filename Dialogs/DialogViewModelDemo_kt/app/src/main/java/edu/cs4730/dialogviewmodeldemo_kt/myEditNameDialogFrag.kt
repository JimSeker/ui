package edu.cs4730.dialogviewmodeldemo_kt

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

/**
 * This is a "custom" dialog, which has an edittext box and returns
 * the value back via a listener.
 * this also pops up the keyboard for the editext box as well.
 */
class myEditNameDialogFrag : DialogFragment() {
    private lateinit var mEditText: EditText
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireActivity())
        val myView = inflater.inflate(R.layout.fragment_edit_name, null)
        mEditText = myView.findViewById<View>(R.id.txt_your_name) as EditText
        mEditText.requestFocus()
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.AppTheme_Dialog  //com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog
            )
        )
        builder.setView(myView).setTitle("Hello")
        val mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        builder.setPositiveButton(
            "Done"
        ) { dialog, id ->
            mViewModel.setItem1(mEditText.text.toString())
            dismiss()
        }
            .setCancelable(false) //don't let them cancel this dialog.  ie use the backbutton to get out of it.
        val dialog: Dialog = builder.create()
        //I want the keyboard to popup, with the dialog, since the edittext has focus.
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return dialog
    }
}
