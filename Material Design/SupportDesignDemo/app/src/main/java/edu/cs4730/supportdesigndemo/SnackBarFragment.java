package edu.cs4730.supportdesigndemo;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * This is an example of how to use the snackbar.  Which is very similar to a toast with
 * two main differences.  first the user can swipe it away and second, you can have a button
 * as well via the setAction call.
 */
public class SnackBarFragment extends Fragment {

    Button btn;
    View myView;

    View.OnClickListener SBonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "You clicked undo", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_snackbar, container, false);
        btn = myView.findViewById(R.id.button01);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This setups and show the snackbar message.
                Snackbar.make(myView, "Hi there?", Snackbar.LENGTH_LONG)  //or LENGTH_SHORT
                    .setAction("Undo?", SBonClickListener)  //this line is optional.
                    .show();
            }
        });
        return myView;
    }
}
