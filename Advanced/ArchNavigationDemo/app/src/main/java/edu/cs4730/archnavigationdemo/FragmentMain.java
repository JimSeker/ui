package edu.cs4730.archnavigationdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.cs4730.archnavigationdemo.databinding.FragmentMainBinding;

/**
 * This fragment is the "main" fragment that is shown first.
 */

public class FragmentMain extends Fragment {

    FragmentMainBinding binding;

    public FragmentMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);

        //add transaction for the button or the convenience method below (uncommented).
       /* binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentMain_to_fragment_two);
            }
        });*/
        //or convenience method
        binding.button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragmentMain_to_fragment_two, null));

        return binding.getRoot();
    }

}
