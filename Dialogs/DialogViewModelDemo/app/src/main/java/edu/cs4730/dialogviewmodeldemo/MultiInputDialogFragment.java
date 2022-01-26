package edu.cs4730.dialogviewmodeldemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;


/**
 * This is a dialogfragment that has two text boxes.
 * The program can start it up with data or just have start with blanks.
 * <p>
 * It will return a string array via the listener that that needs to be implemented by the activity (or fragment?)
 */
public class MultiInputDialogFragment extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String name;  // name
    private String amount;  //amount

    public MultiInputDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.  //name
     * @param param2 Parameter 2.  //amount
     * @return A new instance of fragment MultiInputDialogFragment.
     */

    public static MultiInputDialogFragment newInstance(String param1, String param2) {
        MultiInputDialogFragment fragment = new MultiInputDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);  //name
        args.putString(ARG_PARAM2, param2);  //amount
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = requireArguments().getString(ARG_PARAM1, "");
        amount = requireArguments().getString(ARG_PARAM2, "");
    }

    EditText et_name, et_amount;

    @Override
    public Dialog onCreateDialog(Bundle SavedIntanceState) {

        LayoutInflater inflater = LayoutInflater.from(requireActivity());
        View myView = inflater.inflate(R.layout.fragment_multi_input_dialog, null);
        et_name = (EditText) myView.findViewById(R.id.et_name);
        et_name.setText(name);
        et_amount = (EditText) myView.findViewById(R.id.et_amount);
        et_amount.setText(amount);
        DataViewModel mViewModel = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(requireActivity(), R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(myView).setTitle("Multi Input Dialog");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                mViewModel.setItem1(et_name.getText().toString());
                mViewModel.setItem2(et_amount.getText().toString());
                dismiss();
            }
        })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    mViewModel.setYesNo(false);
                    dialog.cancel();
                }
            });

        return builder.create();

    }
}
