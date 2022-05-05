package edu.cs4730.archnavigationdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Nothing really to see here.  It's all about the fragments and Navigation demo.
 * This is handled by the layout and res/navigation.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
