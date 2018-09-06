package edu.cs4730.listviewswiperefresh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/*
 * nothing much to see here.  Go the MainFragment.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment()).commit();
        }
    }


}
