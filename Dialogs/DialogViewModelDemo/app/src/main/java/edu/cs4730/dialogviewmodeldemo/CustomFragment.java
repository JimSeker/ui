package edu.cs4730.dialogviewmodeldemo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.dialogviewmodeldemo.databinding.FragmentCustomBinding;
import edu.cs4730.dialogviewmodeldemo.databinding.LayoutCustomDialogBinding;

/**
 * This shows how to use the dialog that have been extended.  Note the listeners are implemented
 * in the mainActivity and call the displaylog method at the bottom of this fragment.
 * This may not be the way you want to implements your listeners.  It was easier for communication of
 * the fragments.  Sometimes you want to the listeners om the dialog itself, if you have access to the
 * necessary functions and data.
 */
public class CustomFragment extends Fragment {

    String TAG = "CustomFragment";
    DataViewModel mViewModel;
    FragmentCustomBinding binding;

    public CustomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCustomBinding.inflate(inflater, container, false);

        //note, I'm not setting up an observer here, but you could do here instead, but mainactivty "could" change the fragment
        //based on data, so for this example it's in main activity, but honesty doesn't need to be.
        mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        binding.btnAlert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_TYPE_ID);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myDialog");
            }
        });

        binding.btnAlert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_GAMEOVER_ID);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myDialog");
            }
        });

        binding.btnAlert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertDialogFragment newDialog = myAlertDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title);
                newDialog.show(requireActivity().getSupportFragmentManager(), "myAlertDialog");
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEditNameDialogFrag newDialog = new myEditNameDialogFrag();
                newDialog.show(requireActivity().getSupportFragmentManager(), "myEditDialog");
            }
        });

        binding.inlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog("Inline Fragment");
            }
        });

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
        if (binding == null) {
            Log.e(TAG, "loggerCustom is null, cannot append item.");
            return;
        }
        binding.loggerCustom.append(item + "\n");
    }


    /**
     * We are going to add data
     * setup a dialog fragment to ask the user for the new item data or category.
     */
    public void showInputDialog(String title) {

        LayoutInflater inflater = getLayoutInflater();
        LayoutCustomDialogBinding binding = LayoutCustomDialogBinding.inflate(inflater);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(binding.getRoot()).setTitle(title);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mViewModel.setItem1("data is " + binding.itemAdded.getText().toString());
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mViewModel.setYesNo(false);
                    dialog.cancel();

                }
            });
        //you can create the dialog or just use the now method in the builder.
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.show();
    }


}
