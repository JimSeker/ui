package edu.cs4730.dialogdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class myEditNameDialogFrag extends DialogFragment implements OnEditorActionListener, OnClickListener {

    private EditNameDialogListener mListener;

    private EditText mEditText;

    public myEditNameDialogFrag() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Hello");

        //for the emulators, added a done button
        view.findViewById(R.id.btn_done).setOnClickListener(this);


        // Show soft keyboard automatically  (except won't work in emulators correctly!!!, when physical keyboard input set. Also no done... )
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener(this);

        return view;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            mListener.onFinishEditDialog(mEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }

    //for the emulators where the keyboard never shows..., added a done button, so same thing as above via the button.
    @Override
    public void onClick(View v) {

        mListener.onFinishEditDialog(mEditText.getText().toString());
        this.dismiss();
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
