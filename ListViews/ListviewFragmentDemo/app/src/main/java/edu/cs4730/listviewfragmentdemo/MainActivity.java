package edu.cs4730.listviewfragmentdemo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*
 * This is an example using the listview in a fragment.  There is very little code here that is not default
 * except the callbacks for the fragment named titlefrag.  There is a layout and layout-land for this
 * so the code also decides if it needs to display a fragment or if it is already showing.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */

public class MainActivity extends ActionBarActivity implements titlefrag.OnFragmentInteractionListener {
    boolean TwoPane = false;


    textFrag myTextFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.container) == null ) {
        //landscape or large mode. both fragments will be displayed on the screen.
            //nothing to do, since it already showing.
            TwoPane = true;
        } else {
            TwoPane = false;
            //add the title fragment.
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new titlefrag())
                    .commit();
            myTextFrag = new textFrag();  //needed for later.
        }
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
        if (TwoPane) {
            //already showing, so just update it.
            ((textFrag) getSupportFragmentManager().findFragmentById(R.id.frag_text)
            ).setText(id);
        }else {
            //now add the arg's and startup the textfragment.
            Bundle args = new Bundle();
            args.putInt("position", id);
            myTextFrag.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.container, myTextFrag, "second" );
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }
    }
}
