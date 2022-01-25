package edu.cs4730.dialogdemo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

/**
 * Shows how to build and call a support alert dialog and set the listeners for it.
 * Also shows a list dialog and listeners as well.
 */
public class SupportDialogFragment extends Fragment {
    Button btn;
    TextView logger;
    String TAG = "SuppportDialogFragment";

    public SupportDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_support_dialog, container, false);
        btn = myView.findViewById(R.id.btn_support_AlertDialog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("Demo Dialog");
            }
        });
        logger = myView.findViewById(R.id.logger_support);
        myView.findViewById(R.id.btn_support_ListDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlistdialog("Demo List Dialog");
            }
        });
        return myView;
    }

    void displaylog(String item) {
        Log.v(TAG, item);
        logger.append(item + "\n");
    }


    /*
     *  The method is not necessary.
     *
     *  This builds an alert dialog, with the positive button set to Yes, Negative button set to NO.
     *  There is a listener if the dialog is canceled (ie the back button is used)
     */

    void showdialog(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setTitle(title);
        builder.setMessage("Play again?");
        //Button Button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displaylog("play again");

            }
        });
        //Negative button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displaylog("exit");

            }
        });
        //If the user uses the back button instead.
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                displaylog("canceled ");

            }
        });
        builder.show();
    }

    /*
     * This shows a list and the use is to select one of them.
     * Note, this dialog doesn't set a cancel listener, like the one above.  so if the user
     * cancels, nothing happens.
     */
    void showlistdialog(String title) {
        final String[] items = {"Remove Walls", "Add Walls",
            "Add/Remove Objects", "Add/Remove Score"};
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setTitle("Choose Type:");
        builder.setSingleChoiceItems(items, -1,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    dialog.dismiss();  //the dismiss is needed here or the dialog stays showing.
                    displaylog(items[item]);
                }
            });
        builder.show();
    }
}
