package edu.cs4730.ListDemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.cs4730.ListDemo.databinding.Simple3FragmentBinding;

public class Simple3_Fragment extends Fragment {
    String TAG = "Simple3_Fragment";

    Simple3FragmentBinding binding;

    public Simple3_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Simple3FragmentBinding.inflate(inflater, container, false);

        binding.ListView01.setClickable(true);

        String[] values = new String[]{"Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "VMS", "DOS", "Other", "Chrome"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, values);
        binding.ListView01.setAdapter(adapter);
        binding.ListView01.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                String item = binding.ListView01.getAdapter().getItem(position).toString();
                Toast.makeText(requireContext(), item + " selected", Toast.LENGTH_LONG).show();
            }
        });

        //now the buttons.
        binding.up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // up button, so move the list up
                int i = binding.ListView01.getSelectedItemPosition();
                //Toast.makeText(requireContext(), "up selected is " + i,Toast.LENGTH_LONG).show();
                if (i == ListView.INVALID_POSITION) { // nothing selected, so select first position.
                    binding.ListView01.setSelection(0);
                }
                if (i > 0) {
                    binding.ListView01.setSelection(i - 1);
                }
            }
        });

        binding.down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // down button, so move the it down
                //int i = binding.ListView01.getSelectedItemPosition();
                //Toast.makeText(requireContext(), "down selected is " + i, Toast.LENGTH_LONG).show();
                binding.ListView01.setSelection(binding.ListView01.getCount());
            }
        });

        return binding.getRoot();
    }

}
