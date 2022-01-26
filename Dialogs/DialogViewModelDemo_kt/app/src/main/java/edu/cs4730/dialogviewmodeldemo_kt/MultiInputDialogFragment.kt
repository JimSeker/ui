package edu.cs4730.dialogviewmodeldemo_kt

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

/**
 * This is a dialogfragment that has two text boxes.
 * The program can start it up with data or just have start with blanks.
 *
 *
 * It will return a string array via the listener that that needs to be implemented by the activity (or fragment?)
 */
class MultiInputDialogFragment : DialogFragment() {
    private lateinit var name : String
    private lateinit var amount : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = requireArguments().getString(ARG_PARAM1, "")
        amount = requireArguments().getString(ARG_PARAM2, "")
    }

    lateinit var et_name: EditText
    lateinit var et_amount: EditText
    override fun onCreateDialog(SavedIntanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireActivity())
        val myView = inflater.inflate(R.layout.fragment_multi_input_dialog, null)
        et_name = myView.findViewById<View>(R.id.et_name) as EditText
        et_name.setText(name)
        et_amount = myView.findViewById<View>(R.id.et_amount) as EditText
        et_amount.setText(amount)
        val mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.AppTheme_Dialog  //com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog
            )
        )
        builder.setView(myView).setTitle("Multi Input Dialog")
        builder.setPositiveButton(
            "Save"
        ) { dialog, id ->
            mViewModel.setItem1(et_name.text.toString())
            mViewModel.setItem2(et_amount.text.toString())
            dismiss()
        }
            .setNegativeButton(
                "Cancel"
            ) { dialog, id ->
                mViewModel.setYesNo(false)
                dialog.cancel()
            }
        return builder.create()
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.  //name
         * @param param2 Parameter 2.  //amount
         * @return A new instance of fragment MultiInputDialogFragment.
         */
        fun newInstance(param1: String?, param2: String?): MultiInputDialogFragment {
            val fragment = MultiInputDialogFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1) //name
            args.putString(ARG_PARAM2, param2) //amount
            fragment.arguments = args
            return fragment
        }
    }
}
