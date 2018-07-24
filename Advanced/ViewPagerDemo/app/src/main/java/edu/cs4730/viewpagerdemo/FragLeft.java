package edu.cs4730.viewpagerdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragLeft extends Fragment {
    TextView tx;
    String savedData;
    String TAG = "Left";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate");
        if (savedInstanceState != null) {
            Log.d(TAG, "OnCreate savedInstanceState");
            savedData = savedInstanceState.getString("text");
        } else {
            savedData = "";  //just making sure.
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "OnActivityCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Left", "OnCreateView");
        View view = inflater.inflate(R.layout.left, container, false);
        tx = (TextView) view.findViewById(R.id.tvleft);
        setText(savedData);
        return view;
    }

    public void setText(String str) {
        tx.setText(tx.getText() + "\n" + str);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView()");

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "OnSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString("text", tx.getText().toString());
    }
}
