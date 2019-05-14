package edu.cs4730.fragcomdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


/*
 * This activity is acting as the go between for the First and Second fragment.
 * The main fragment is used to use launch either fragment.
 *
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,
    FirstFragment.OnFragmentInteractionListener1,
    SecondFragment.OnFragmentInteractionListener2 {

    int num_one = 0;  //number of times firstfragment was called.
    int num_two = 0;  //number of times secondfragment was called.
    String TAG = "MainActivity";
    //used to move the fragments.
    FragmentManager fragmentManager;
    int whichfragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        //setup the mainFragment to show.
        fragmentManager.beginTransaction().add(R.id.container, new MainFragment()).commit();
    }


    /*
     * This is the callback for MainFragment.  It takes as which number, which well is the fragment
     * to launch.
     */
    @Override
    public void onFragmentInteraction(int which) {
        //going to change via the transaction manager, instead of just a simple replace.
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //remove the current fragment...
        //transaction.remove(fragmentManager.findFragmentById(R.id.container));
        if (which == 1) { //first fragment
            //replace with first fragment
            transaction.replace(R.id.container, FirstFragment.newInstance(String.valueOf(num_one), "Called From MainFrag"));
            num_one++;
            whichfragment = 1;
        } else { //must be 2 (hopefully!)
            //replace with first fragment
            transaction.replace(R.id.container, SecondFragment.newInstance(String.valueOf(num_two), "Called From MainFrag"));
            num_two++;
            whichfragment = 2;
        }
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


    /*
     * This the callback interface for FirstFragment.  We are getting string of data
     * which we will then send the SecondFragment.
     */
    @Override
    public void onFragmentInteraction1(String Data) {

        //now change to the SecondFragment, pressing the back button should go to main fragment.
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //remove firstfragment from the stack and replace it with two.
        transaction.replace(R.id.container, SecondFragment.newInstance(String.valueOf(num_two), Data));
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        num_two++;
    }

    /*
     * This the callback interface for SecondFragment.  We are getting string of data
     * which we will then send the FirstFragment.
     */
    @Override
    public void onFragmentInteraction2(String Data) {

        //now change to the FirstFragment, pressing the back button should go to main fragment.
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //remove Secondfragment from the stack and replace it with one.
        transaction.replace(R.id.container, FirstFragment.newInstance(String.valueOf(num_one), Data));
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        num_one++;
    }


    /*
     *
     * If we wanted to deal with the back button, this is the method for it.
     * (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
