package edu.cs4730.viewbindingfragdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.cs4730.viewbindingfragdemo.databinding.FragmentOneBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  simple fragment has have a edittext and button.  using the viewbinding so you
 *  don't need findviewbyid.  and you know everything is not null, plus this
 *  also has an advantage you have the same id names in different xml and not have an issues
 *  with most studio picking the wrong one.
 */
public class OneFragment extends Fragment {

    private FragmentOneBinding binding;
    private OnFragmentInteractionListener1 mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOneBinding.inflate(inflater, container, false);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    String value = "stuff";
                    if (binding.etName.getText().toString().compareTo("") != 0)
                        value = binding.etName.getText().toString();
                    mListener.onFragmentInteraction1(value);
                }
            }
        });

        //needs to return a view.  so get it for the return
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = requireActivity();
        try {
            mListener = (OnFragmentInteractionListener1) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener1 {
        void onFragmentInteraction1(String Data);
    }
}