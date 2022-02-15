package edu.cs4730.supportdesigndemo;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * This example uses both a Floating Action button and a snackbar.
 * The fab is at the bttom right and it will move up and out of the way, so the snackbar will not
 * cover it up.  There is very little code in here to do it.  It's in the xml layout, android.support.design.widget.CoordinatorLayout
 * to do the work.  The snackbar is sent the coordinatorlayout, and the layout then "moves" the fab up (out of the way of snackbar)
 * so that there is no over lap.
 */
public class SB_FABFragment extends Fragment {

    Button btn;
    CoordinatorLayout mCoordinatorLayout;

    View.OnClickListener SBonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(requireContext(), "Yea!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_sbfab, container, false);
        mCoordinatorLayout = myView.findViewById(R.id.coordinatorlayout1);
        btn = myView.findViewById(R.id.button02);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mCoordinatorLayout, "Did the FAB move?", Snackbar.LENGTH_LONG)
                    .setAction("Yes?", SBonClickListener)  //this line is optional!
                    .show();
            }
        });
        myView.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mCoordinatorLayout, "I knew you had to click the FAB.", Snackbar.LENGTH_LONG)
                    .show();
            }
        });

        return myView;
    }


}
