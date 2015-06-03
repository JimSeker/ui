package edu.cs4730.supportdesigndemo;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SB_FABFragment extends Fragment {

    Button btn;
    CoordinatorLayout mCoordinatorLayout;

    public SB_FABFragment() {
        // Required empty public constructor
    }
    View.OnClickListener SBonClickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
            Toast.makeText(getActivity(), "Yea!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_sbfab, container, false);
        mCoordinatorLayout = (CoordinatorLayout) myView.findViewById(R.id.coordinatorlayout1);
        btn = (Button) myView.findViewById(R.id.button02);
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
