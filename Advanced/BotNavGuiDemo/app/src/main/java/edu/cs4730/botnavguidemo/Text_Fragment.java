package edu.cs4730.botnavguidemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.botnavguidemo.databinding.TextFragmentBinding;

public class Text_Fragment extends Fragment {
    String TAG = "Text_fragment";
    TextFragmentBinding binding;
    public Text_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Left", "OnCreateView");
        binding = TextFragmentBinding.inflate(inflater, container, false);

        binding.big.setText("Large TextView");
        return binding.getRoot();
    }
}
