package edu.cs4730.archnavigationdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Second fragment to seen by the use.  This also shows how to pass data via a bundle or safe_args.
 */

public class Fragment_two extends Fragment {
    EditText et;

    public Fragment_two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_two, container, false);
        myView.findViewById(R.id.two_back)
            .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Navigation.findNavController(view).navigateUp(); //go back!
                                    }
                                }

            );
        //now for passing data.
        et = myView.findViewById(R.id.editText);

        Button btn = myView.findViewById(R.id.two_next);
        //add transaction for the button.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the "action" and add the parameters to it.  Then navigate to it.
                Fragment_twoDirections.ActionFragmentTwoToFragmentThree action = Fragment_twoDirections.actionFragmentTwoToFragmentThree();
                action.setMessage(et.getText().toString());
                action.setNumber(3012);
                Navigation.findNavController(view).navigate(action);

            }
        });

        Button btn2 = myView.findViewById(R.id.two_next_bundle);
        //add transaction for the button.
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this uses a bundle instead of safe args.  Also is a lot simpler.  No addition info in needed in nav.
                Bundle bundle = new Bundle();
                bundle.putString("message", et.getText().toString());
                bundle.putInt("number", 3012);

                Navigation.findNavController(view).navigate(R.id.action_two_to_bundle, bundle);
            }
        });

        return myView;
    }


}
