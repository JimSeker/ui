package edu.cs4730.dialogviewmodeldemo_kt

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.dialogviewmodeldemo_kt.databinding.FragmentEditNameDialogBinding

/**
 * This is a "custom" dialog, which has an edittext box and returns
 * the value back via a listener.
 * this also pops up the keyboard for the editext box as well.
 */
class myEditNameDialogFrag : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = layoutInflater
        val binding: FragmentEditNameDialogBinding = FragmentEditNameDialogBinding.inflate(inflater)


        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.AppTheme_Dialog  //com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog
            )
        )
        builder.setView(binding.root).setTitle("Hello")
        val mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        builder.setPositiveButton(
            "Done"
        ) { dialog, id ->
            mViewModel.setItem1(binding.txtYourName.text.toString())
            dismiss()
        }
            .setCancelable(false) //don't let them cancel this dialog.  ie use the backbutton to get out of it.

        return builder.create()
    }
}
