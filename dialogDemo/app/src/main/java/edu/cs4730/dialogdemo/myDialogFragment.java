package edu.cs4730.dialogdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class myDialogFragment extends DialogFragment {
	static final int DIALOG_TYPE_ID = 0;
	static final int DIALOG_GAMEOVER_ID = 1;
	
	/*
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
	
    /*
     * Instead of using onCreateView, we use the onCreateDialog
     * This uses the ID above to determine which dialog to build.
     * Note, this calls back into the activity, so the methods
     * doItem(String), doPositiveClick(), and doNegativeClick() need to be implemented. (not call backs either)
     * 
     * (non-Javadoc)
     * @see android.support.v4.app.DialogFragment#onCreateDialog(android.os.Bundle)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int id = getArguments().getInt("id");

        Dialog dialog = null;
        AlertDialog.Builder builder;
        switch (id) {
          case DIALOG_TYPE_ID:
            final String[] items = { "Remove Walls", "Add Walls",
                "Add/Remove Objects", "Add/Remove Score" };
            builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Type:");
            builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int item) {
                      dialog.dismiss();  //the dismiss is needed here or the dialog stays showing.
                	  ((DialFragActivity)getActivity()).doItem(items[item]);
                  }
                });
            dialog = builder.create();

            break;
          case DIALOG_GAMEOVER_ID:
            builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  ((DialFragActivity)getActivity()).doPositiveClick();
                  }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  ((DialFragActivity)getActivity()).doNegativeClick();
                  }
                });
            dialog = builder.create();
            break;
        }
        return dialog;
    }
}
