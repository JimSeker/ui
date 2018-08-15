package edu.cs4730.fragdemosimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
  This is a very simple demo of changing between two fragments.
  This really not a good way to do this and is for demo purposes.  I needed something
  really simple to start out with.
 */

public class MainActivity extends AppCompatActivity {

    boolean firstfragment = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if this a not new, then place add firstfragment to the framelayout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new oneFragment())
                .commit();
        }

        //find the button and setup the listener.
        Button btn1 = findViewById(R.id.button01);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstfragment) {
                    //first fragment is showing, so replace it with the second one.
                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new twoFragment())
                        .commit();
                    firstfragment = false;
                } else {
                    //second fragment is showing, so replace it with the second one.
                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new oneFragment())
                        .commit();
                    firstfragment = true;

                }
            }
        });
    }
}
