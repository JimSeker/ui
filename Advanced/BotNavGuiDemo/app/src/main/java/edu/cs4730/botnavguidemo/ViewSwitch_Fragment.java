package edu.cs4730.botnavguidemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.botnavguidemo.databinding.ViewswitchFragmentBinding;

/**
 * This shows how the view switcher can work.
 * It's in interesting one, because we can change a "static" layout pretty easy.
 */
public class ViewSwitch_Fragment extends Fragment {
    String TAG = "ViewSwitch_fragment";
    ViewswitchFragmentBinding binding;

    public ViewSwitch_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ViewswitchFragmentBinding.inflate(inflater, container, false);

        //setup the button
        binding.vsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewSwitcher1.showNext();
            }
        });

        //ViewFlipper code
        //set animation, which can be used in viewswitcher as well
        binding.viewFlipper1.setInAnimation(AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_in_left)); //or android.R.anim.fade_in
        binding.viewFlipper1.setOutAnimation(AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_out_right));  //or android.R.anim.fade_out
        //setup the button
        binding.vfButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewFlipper1.showNext();
            }
        });


        binding.vfButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.viewFlipper1.isFlipping()) { //is animated so stop it.
                    binding.viewFlipper1.stopFlipping();
                    binding.vfButton2.setText("Start animation");
                } else {  //start animation
                    binding.viewFlipper1.startFlipping();
                    //Can also setup how long, with setFlipInterval( int milliseconds)  appears to be 1 second default.
                    binding.vfButton2.setText("Stop animation");
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }
}
