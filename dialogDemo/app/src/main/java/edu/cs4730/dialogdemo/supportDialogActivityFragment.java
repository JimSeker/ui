package edu.cs4730.dialogdemo;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class supportDialogActivityFragment extends Fragment {

    Button btn;
    TextView logger;
    String TAG = "SuppportDialogFragment";

    public supportDialogActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView =  inflater.inflate(R.layout.fragment_support_dialog, container, false);
        btn = (Button) myView.findViewById(R.id.btn_support_AlertDialog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog("Demo Dialog");
            }
        });
        logger = (TextView) myView.findViewById(R.id.logger_support);

        return myView;
    }

    void displaylog(String item) {
        Log.v(TAG, item);
        logger.append(item + "\n");
    }

    void showdialog(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage("Play again?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displaylog("play again");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displaylog("exit");

            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                displaylog("canceled ");

            }
        });
        builder.show();
    }
}
