package edu.cs4730.dialogviewmodeldemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * this extends a Dialog fragment to create an alterdialog fragment
 */

public class myAlertDialogFragment extends DialogFragment {

    public static myAlertDialogFragment newInstance(int title) {
        myAlertDialogFragment frag = new myAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = requireArguments().getInt("title");
        DataViewModel mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        return new AlertDialog.Builder(requireActivity())
            //.setIcon(R.drawable.alert_dialog_icon)
            .setTitle(title)
            .setPositiveButton(R.string.alert_dialog_ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mViewModel.setYesNo(true);
                    }
                }
            )
            .setNegativeButton(R.string.alert_dialog_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mViewModel.setYesNo(false);
                    }
                }
            )
            .create();
    }

}
