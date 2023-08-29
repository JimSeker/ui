package edu.cs4730.multiwindowsdemo;

import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import edu.cs4730.multiwindowsdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        logthis("onCreate");

        checkmode();

    }

    public void checkmode() {
        if (isInMultiWindowMode()) {
            logthis("MultiWindowmode is true");
        } else if (isInPictureInPictureMode()) {
            logthis("PIP is true");
        } else {
            logthis("Normal, not multiwindow mode");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        logthis("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logthis("onResume");
        checkmode();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        logthis("onMultiWindowModeChanged: " + isInMultiWindowMode);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        logthis("onPostCreate 2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logthis("onDestroy");
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        logthis("onPostCreate 1");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logthis("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logthis("onStop");
    }

    void logthis(String item) {
        Log.d(TAG, item);
        binding.logger.append(item);
        binding.logger.append("\n");
    }
}
