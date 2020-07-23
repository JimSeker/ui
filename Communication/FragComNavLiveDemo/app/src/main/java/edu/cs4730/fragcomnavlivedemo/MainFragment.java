package edu.cs4730.fragcomnavlivedemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    Button btn1, btn2;
    DataViewModel mViewModel;
    TextView tv1, tv2;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_main, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        tv1 = myView.findViewById(R.id.textView3);
        tv2 = myView.findViewById(R.id.textView4);
        //button to call firstfragment
        btn1 = myView.findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the navigation to call the action to change to first fragment.
                //can change to the first fragment and send a simple string as well.
                mViewModel.incr_One();
                mViewModel.setItem("Called From MainFrag");
                Navigation.findNavController(v).navigate(R.id.action_main_to_first);
            }
        });
        //button to call secondfragment.
        btn2 = myView.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is calling the navigation to call the action to change to second fragment.
                mViewModel.incr_Two();
                mViewModel.setItem("Called From MainFrag");
                Navigation.findNavController(v).navigate(R.id.action_main_to_second);
            }
        });

        mViewModel.getoneLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
                tv1.setText("Parameter1: " +v);
            }
        });
        mViewModel.gettwoLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer v) {
                tv2.setText("Parameter1: " +v);
            }
        });


        return myView;
    }

}