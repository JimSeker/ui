package edu.cs4730.botnavguidemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.botnavguidemo.databinding.ButtonFragmentBinding;

public class Button_Fragment extends Fragment implements View.OnClickListener {

    String TAG = "Button_Fragment";
    ButtonFragmentBinding binding;

    public Button_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ButtonFragmentBinding.inflate(inflater, container, false);

        binding.button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.output.setText("Output:\n Button1");
            }
        });

        binding.button02.setOnClickListener(this);
        binding.button03.setOnClickListener(this);
        //binding makes it very simple to setup listeners, unless with findviewbyid there is 3 ways.
        //note setting the listener in the xml android:onclick= will call the MainActivity, not the fragment!

        return binding.getRoot();
    }

    /**
     * This on is the for the implements View.OnClickListener
     */
    @Override
    public void onClick(View v) {
        if (v == binding.button02) {  //it's button 2
            Toast.makeText(requireContext(), "Button 2 was clicked", Toast.LENGTH_SHORT).show();
        } else if (v == binding.button03) {  //it's button 3
            binding.output.setText("Output:\nButton3");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }
}
