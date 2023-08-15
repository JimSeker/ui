package edu.cs4730.dialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import edu.cs4730.dialogdemo.databinding.FragmentCustomBinding;
import edu.cs4730.dialogdemo.databinding.LayoutCustomDialogBinding;

/**
 * This shows how to use the dialog that have been extended.  Note the listeners are implemented
 * in the mainActivity and call the displaylog method at the bottom of this fragment.
 * This may not be the way you want to implements your listeners.  It was easier for communication of
 * the fragments.  Sometimes you want to the listeners om the dialog itself, if you have access to the
 * necessary functions and data.
 */
public class CustomFragment extends Fragment {

    String TAG = "CustomFragment";

    // TextView logger;
    FragmentCustomBinding binding;

    public CustomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View myView = inflater.inflate(R.layout.fragment_custom, container, false);
        binding = FragmentCustomBinding.inflate(inflater, container, false);


        //logger = myView.findViewById(R.id.logger_custom);
        //myView.findViewById(R.id.btn_alert1).setOnClickListener(new View.OnClickListener() {
        binding.btnAlert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_TYPE_ID);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myDialog");
            }

        });
        //myView.findViewById(R.id.btn_alert2).setOnClickListener(new View.OnClickListener() {
        binding.btnAlert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_GAMEOVER_ID);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myDialog");
            }
        });
        //myView.findViewById(R.id.btn_alert3).setOnClickListener(new View.OnClickListener() {
        binding.btnAlert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myAlertDialogFragment newDialog = myAlertDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myAlertDialog");
            }
        });
        //myView.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myEditNameDialogFrag newDialog = new myEditNameDialogFrag();
                newDialog.show(requireActivity().getSupportFragmentManager(), "myEditDialog");
            }
        });
        //myView.findViewById(R.id.inline_button).setOnClickListener(new View.OnClickListener() {
        binding.inlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog("Inline Fragment");
            }
        });
        //myView.findViewById(R.id.mutli_input_btn).setOnClickListener(new View.OnClickListener() {
        binding.mutliInputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiInputDialogFragment newDialog = MultiInputDialogFragment.newInstance("Jim", null);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myMultiInputDialog");
            }
        });

        return binding.getRoot();
    }

    /**
     * simple method to display data to the logger textview.
     */
    void displaylog(String item) {
        Log.v(TAG, item);
        binding.loggerCustom.append(item + "\n");
    }

    /**
     * We are going to add data
     * setup a dialog fragment to ask the user for the new item data or category.
     */
    public void showInputDialog(String title) {

        LayoutInflater inflater = LayoutInflater.from(requireActivity());
        LayoutCustomDialogBinding binding = LayoutCustomDialogBinding.inflate(inflater);
        //final View textenter = inflater.inflate(R.layout.layout_custom_dialog, null);
        //final EditText userinput = (EditText) textenter.findViewById(R.id.item_added);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        //builder.setView(textenter).setTitle(title);
        builder.setView(binding.getRoot()).setTitle(title);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //displaylog("data is " + userinput.getText().toString());
                displaylog("data is " + binding.itemAdded.getText().toString());
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
