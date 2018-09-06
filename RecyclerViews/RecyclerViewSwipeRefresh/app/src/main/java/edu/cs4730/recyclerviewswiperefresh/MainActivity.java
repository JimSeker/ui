package edu.cs4730.recyclerviewswiperefresh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//Nothing to see here.  check the mainFragment and Adapter code.

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
