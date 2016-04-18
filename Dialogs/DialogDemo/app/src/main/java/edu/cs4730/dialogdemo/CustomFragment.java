package edu.cs4730.dialogdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This shows how to use the dialog that have been extended.  Note the listeners are implemented
 * in the mainActivity and call the displaylog method at the bottom of this fragment.
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
        logger = (TextView) myView.findViewById(R.id.logger_custom);
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
        return myView;
    }

    void displaylog(String item) {
        Log.v(TAG, item);
        logger.append(item + "\n");
    }


}
