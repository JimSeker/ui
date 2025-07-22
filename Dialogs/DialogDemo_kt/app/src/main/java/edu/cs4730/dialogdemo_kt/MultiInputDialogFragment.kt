package edu.cs4730.dialogdemo_kt

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.DialogFragment
import edu.cs4730.dialogdemo_kt.databinding.FragmentMultiInputDialogBinding

/**
 * This is a dialogfragment that has two text boxes.
 * The program can start it up with data or just have start with blanks.
 *
 * It will return a string array via the listener that that needs to be implemented by the activity (or fragment?)
 */
class MultiInputDialogFragment : DialogFragment() {
    private var name: String? = null
    private var amount: String? = null
    private var mListener: OnDialogFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            name = requireArguments().getString(ARG_PARAM1, null)
            amount = requireArguments().getString(ARG_PARAM2, null)
        }
    }


    override fun onCreateDialog(SavedIntanceState: Bundle?): Dialog {
        val inflater = layoutInflater
        val binding: FragmentMultiInputDialogBinding =
            FragmentMultiInputDialogBinding.inflate(inflater)

        if (name != null) binding.etName.setText(name)
        if (amount != null) binding.etAmount.setText(amount)

        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.ThemeOverlay_AppCompat_Dialog
            )
        )
        builder.setView(binding.root).setTitle("Multi Input Dialog")
        builder.setPositiveButton(
            "Save"
        ) { dialog, id ->
            val returnlist = arrayOf(
                binding.etName.text.toString(),
                binding.etAmount.text.toString()
            )
            //send the list back to the MainActivity to process.
            mListener!!.onMultiInputInteraction(returnlist)
            dismiss()
        }
            .setNegativeButton(
                "Cancel"
            ) { dialog, id -> dialog.cancel() }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnDialogFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    internal interface OnDialogFragmentInteractionListener {
        fun onMultiInputInteraction(items: Array<String>)
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
