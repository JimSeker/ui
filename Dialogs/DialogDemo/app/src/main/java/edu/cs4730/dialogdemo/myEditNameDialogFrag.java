package edu.cs4730.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;

import edu.cs4730.dialogdemo.databinding.FragmentEditNameBinding;


/**
 * This is a "custom" dialog, which has an edittext box and returns
 * the value back via a listener.
 * this also pops up the keyboard for the editext box as well.
 */

public class myEditNameDialogFrag extends DialogFragment {

    private EditNameDialogListener mListener;

    public myEditNameDialogFrag() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(requireActivity());
        FragmentEditNameBinding binding = FragmentEditNameBinding.inflate(inflater);

        //the keyboard should come up and focus should be set to this input box.
        binding.txtYourName.requestFocus();

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(binding.getRoot()).setTitle("Hello");

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mListener.onFinishEditDialog(binding.txtYourName.getText().toString());
                dismiss();
            }
        }).setCancelable(false);  //don't let them cancel this dialog.  ie use the backbutton to get out of it.

        Dialog dialog = builder.create();
        //I want the keyboard to popup, with the dialog, since the edittext has focus.
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return dialog;
    }


    //all the callback stuff.
    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (EditNameDialogListener) requireActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(requireActivity().toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
