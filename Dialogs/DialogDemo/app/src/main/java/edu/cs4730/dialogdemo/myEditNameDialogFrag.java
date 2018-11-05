package edu.cs4730.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;

/*
 *  This is a "custom" dialog, which has an edittext box and returns
 *  the value back via a listener.
  * this also pops up the keyboard for the editext box as well.
 */

public class myEditNameDialogFrag extends DialogFragment {

    private EditNameDialogListener mListener;

    private EditText mEditText;

    public myEditNameDialogFrag() {
        // Empty constructor required for DialogFragment
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View myView = inflater.inflate(R.layout.fragment_edit_name, null);
        mEditText = (EditText) myView.findViewById(R.id.txt_your_name);
        mEditText.requestFocus();
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(myView).setTitle("Hello");

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mListener.onFinishEditDialog(mEditText.getText().toString());
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
            mListener = (EditNameDialogListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
