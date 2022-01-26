package edu.cs4730.dialogmodelviewdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class myDialogFragment extends DialogFragment {
    static final int DIALOG_TYPE_ID = 0;
    static final int DIALOG_GAMEOVER_ID = 1;

    /**
     * Create an instance of this fragment.  Uses the "ID numbers" above to determine which
     * dialog box to display.
     */
    public static myDialogFragment newInstance(int id) {
        myDialogFragment frag = new myDialogFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        frag.setArguments(args);
        return frag;
    }

    /**
     * Instead of using onCreateView, we use the onCreateDialog
     * This uses the ID above to determine which dialog to build.
     * <p>
     * Uses the Viewmodel to send back information.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int id = requireArguments().getInt("id");
        DataViewModel mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        Dialog dialog = null;
        AlertDialog.Builder builder;
        switch (id) {
            case DIALOG_TYPE_ID:
                final String[] items = {"Remove Walls", "Add Walls",
                    "Add/Remove Objects", "Add/Remove Score"};
                builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("Choose Type:");
                builder.setSingleChoiceItems(items, -1,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            dialog.dismiss();  //the dismiss is needed here or the dialog stays showing.
                            mViewModel.setItem1(items[item]);
                        }
                    });
                dialog = builder.create();

                break;
            case DIALOG_GAMEOVER_ID:
                builder = new AlertDialog.Builder(requireActivity());
                builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mViewModel.setYesNo(true);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mViewModel.setYesNo(false);
                        }
                    });
                dialog = builder.create();
                break;
        }
        return dialog;
    }

}
