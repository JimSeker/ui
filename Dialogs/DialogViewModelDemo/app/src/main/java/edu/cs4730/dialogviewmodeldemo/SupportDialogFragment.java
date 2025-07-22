package edu.cs4730.dialogviewmodeldemo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import edu.cs4730.dialogviewmodeldemo.databinding.FragmentSupportDialogBinding;

/**
 * Shows how to build and call a support alert dialog and set the listeners for it.
 * Also shows a list dialog and listeners as well.
 */
public class SupportDialogFragment extends Fragment {

    String TAG = "SuppportDialogFragment";
    FragmentSupportDialogBinding binding;

    public SupportDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSupportDialogBinding.inflate(inflater, container, false);
        binding.btnSupportAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("Demo Dialog");
            }
        });

        binding.btnSupportListDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlistdialog("Demo List Dialog");
            }
        });
        return binding.getRoot();
    }

    void displaylog(String item) {
        Log.v(TAG, item);
        binding.loggerSupport.append(item + "\n");
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

    /**
     * This shows a list and the use is to select one of them.
     * Note, this dialog doesn't set a cancel listener, like the one above.  so if the user
     * cancels, nothing happens.
     */
    void showlistdialog(String title) {
        final String[] items = {"Remove Walls", "Add Walls",
            "Add/Remove Objects", "Add/Remove Score"};
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setTitle(title);
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
