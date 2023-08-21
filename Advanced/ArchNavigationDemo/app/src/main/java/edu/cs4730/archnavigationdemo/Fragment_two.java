package edu.cs4730.archnavigationdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cs4730.archnavigationdemo.databinding.FragmentTwoBinding;

/**
 * Second fragment to seen by the use.  This also shows how to pass data via a bundle or safe_args.
 */
public class Fragment_two extends Fragment {
    FragmentTwoBinding binding;

    public Fragment_two() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false);
        binding.twoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp(); //go back!
            }
        });


        //add transaction for the button.
        binding.twoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the "action" and add the parameters to it.  Then navigate to it.
                Fragment_twoDirections.ActionFragmentTwoToFragmentThree action = Fragment_twoDirections.actionFragmentTwoToFragmentThree();
                action.setMessage(binding.editText.getText().toString());
                action.setNumber(3012);
                Navigation.findNavController(view).navigate((NavDirections) action);
            }
        });

        //add transaction for the button.
        binding.twoNextBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this uses a bundle instead of safe args.  Also is a lot simpler.  No addition info in needed in nav.
                Bundle bundle = new Bundle();
                bundle.putString("message", binding.editText.getText().toString());
                bundle.putInt("number", 3012);
                Navigation.findNavController(view).navigate(R.id.action_two_to_bundle, bundle);
            }
        });

        return binding.getRoot();
    }


}
