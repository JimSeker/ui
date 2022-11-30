package edu.cs4730.viewbindingfragdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import edu.cs4730.viewbindingfragdemo.databinding.FragmentOneBinding;
import edu.cs4730.viewbindingfragdemo.databinding.FragmentTwoBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 *  simple fragment has a button.  using the viewbinding so you
 *  don't need findviewbyid.  and you know everything is not null, plus this
 *  also has an advantage you have the same id names in different xml and not have an issues
 *  with most studio picking the wrong one.
 */
public class TwoFragment extends Fragment {


    private FragmentTwoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTwoBinding.inflate(inflater, container, false);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Hi there", Toast.LENGTH_SHORT).show();
            }
        });


        //needs a view so return one.
        return  binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}