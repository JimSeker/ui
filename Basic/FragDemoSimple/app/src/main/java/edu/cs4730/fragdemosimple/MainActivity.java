package edu.cs4730.fragdemosimple;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/*
  This is a very simple demo of changing between two fragments.
  This really not a good way to do this and is for demo purposes.  I needed something
  really simple to start out with.
 */

public class MainActivity extends ActionBarActivity {

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
        Button btn1 = (Button) findViewById(R.id.button01);
        btn1.setOnClickListener( new View.OnClickListener(){
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
