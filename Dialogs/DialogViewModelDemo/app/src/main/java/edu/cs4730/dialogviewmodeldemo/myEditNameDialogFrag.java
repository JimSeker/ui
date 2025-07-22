package edu.cs4730.dialogviewmodeldemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import edu.cs4730.dialogviewmodeldemo.databinding.FragmentEditNameBinding;

/**
 * This is a "custom" dialog, which has an edittext box and returns
 * the value back via a listener.
 * this also pops up the keyboard for the editext box as well.
 */

public class myEditNameDialogFrag extends DialogFragment {

    public myEditNameDialogFrag() {
        // Empty constructor required for DialogFragment
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getLayoutInflater();
        FragmentEditNameBinding binding = FragmentEditNameBinding.inflate(inflater);

        //the keyboard should come up and focus should be set to this input box.
        binding.txtYourName.requestFocus();

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(binding.getRoot()).setTitle("Hello");
        DataViewModel mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mViewModel.setItem1(binding.txtYourName.getText().toString());
                dismiss();
            }
        }).setCancelable(false);  //don't let them cancel this dialog.  ie use the backbutton to get out of it.

        return builder.create();
    }
}
