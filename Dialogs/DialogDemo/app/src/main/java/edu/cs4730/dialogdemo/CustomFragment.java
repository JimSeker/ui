package edu.cs4730.dialogdemo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This shows how to use the dialog that have been extended.  Note the listeners are implemented
 * in the mainActivity and call the displaylog method at the bottom of this fragment.
 * This may not be the way you want to implements your listeners.  It was easier for communication of
 * the fragments.  Sometimes you want to the listemers om the dialog itself, if you have access to the
 * necessary functions and data.
 */
public class CustomFragment extends Fragment {

    String TAG = "CustomFragment";

    TextView logger;

    public CustomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_custom, container, false);
        logger = myView.findViewById(R.id.logger_custom);
        myView.findViewById(R.id.btn_alert1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_TYPE_ID);
                newDialog.show(getFragmentManager(), "myDialog");
            }

        });
        myView.findViewById(R.id.btn_alert2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_GAMEOVER_ID);
                newDialog.show(getFragmentManager(), "myDialog");
            }
        });
        myView.findViewById(R.id.btn_alert3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myAlertDialogFragment newDialog = myAlertDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title);
                newDialog.show(getFragmentManager(), "myAlertDialog");
            }
        });
        myView.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myEditNameDialogFrag newDialog = new myEditNameDialogFrag();
                newDialog.show(getFragmentManager(), "myEditDialog");
            }
        });
        myView.findViewById(R.id.inline_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog("Inline Fragment");
            }
        });
        myView.findViewById(R.id.mutli_input_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MultiInputDialogFragment newDialog = new MultiInputDialogFragment();
                MultiInputDialogFragment newDialog = MultiInputDialogFragment.newInstance("Jim", null);
                newDialog.show(getFragmentManager(), "myMultiInputDialog");
            }
        });

        return myView;
    }

    /*
      * simple method to display data to the logger textview.
     */
    void displaylog(String item) {
        Log.v(TAG, item);
        logger.append(item + "\n");
    }


      /*
   We are going to add data
   * setup a dialog fragment to ask the user for the new item data or category.
   */

    public void showInputDialog(String title) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View textenter = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText userinput = (EditText) textenter.findViewById(R.id.item_added);
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_AppCompat));
        builder.setView(textenter).setTitle(title);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {

                displaylog("data is " + userinput.getText().toString());
                //Toast.makeText(getBaseContext(), userinput.getText().toString(), Toast.LENGTH_LONG).show();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        displaylog("dialog canceled");
                        dialog.cancel();

                    }
                });
        //you can create the dialog or just use the now method in the builder.
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.show();
    }


}
