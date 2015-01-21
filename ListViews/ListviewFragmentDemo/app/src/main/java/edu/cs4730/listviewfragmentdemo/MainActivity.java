package edu.cs4730.listviewfragmentdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*
 * This is an example using the listview in a fragment.  There is very little code here that is not default
 * except the callbacks for the fragment named titlefrag.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */

public class MainActivity extends ActionBarActivity implements titlefrag.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    @Override
    public void onItemSelected(int id) {
        ((textFrag) getSupportFragmentManager().findFragmentById(R.id.frag_text)
        ).setText(id);
    }
}
