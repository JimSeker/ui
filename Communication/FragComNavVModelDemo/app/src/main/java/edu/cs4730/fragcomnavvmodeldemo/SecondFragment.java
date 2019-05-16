package edu.cs4730.fragcomnavvmodeldemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

/**
 *  simple example use a viewmodel as plain old java object.  Except it shared between all the fragments and MainActivity.
 *    Note it would even better with LiveData, but that is another example.
 */
public class SecondFragment extends Fragment {

    TextView tv1, tv2;
    Button btn1;
    DataViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_second, container, false);

        mViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);


        tv1 = myView.findViewById(R.id.sf_tv1);
        tv2 = myView.findViewById(R.id.sf_tv2);
        btn1 = myView.findViewById(R.id.sf_btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the interface, which call into the activity, so it
                mViewModel.num_one++;
                mViewModel.setItem("Called by SecondFragment");
                Navigation.findNavController(v).navigate(R.id.action_second_to_first);
            }
        });
        tv1.setText("Parameter1: " +mViewModel.num_two);
        tv2.setText("Parameter1: " +mViewModel.getItem());
        return myView;
    }


}
