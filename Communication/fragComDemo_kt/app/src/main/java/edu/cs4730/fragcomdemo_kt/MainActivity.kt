package edu.cs4730.fragcomdemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import edu.cs4730.fragcomdemo_kt.databinding.ActivityMainBinding


/**
 * This activity is acting as the go between for the First and Second fragment.
 * The main fragment is used to use launch either fragment.
 */
class MainActivity : AppCompatActivity(), MainFragment.OnFragmentInteractionListener,
    FirstFragment.OnFragmentInteractionListener1, SecondFragment.OnFragmentInteractionListener2 {
    var num_one = 0 //number of times firstfragment was called.
    var num_two = 0 //number of times secondfragment was called.
    var TAG = "MainActivity"
    lateinit var  binding: ActivityMainBinding
    //used to move the fragments.
    lateinit var fragmentManager: FragmentManager
    var whichfragment = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentManager = supportFragmentManager
        //setup the mainFragment to show.
        fragmentManager.beginTransaction().add(binding.container.id, MainFragment()).commit()
    }


    /*
     * This is the callback for MainFragment.  It takes as which number, which well is the fragment
     * to launch.
     */
    override fun onFragmentInteraction(which: Int) {
        //going to change via the transaction manager, instead of just a simple replace.
        val transaction = fragmentManager.beginTransaction()

        //remove the current fragment...
        //transaction.remove(fragmentManager.findFragmentById(R.id.container));
        whichfragment = if (which == 1) { //first fragment
            //replace with first fragment
            transaction.replace(
                binding.container.id,
                FirstFragment.newInstance(num_one.toString(), "Called From MainFrag")
            )
            num_one++
            1
        } else { //must be 2 (hopefully!)
            //replace with first fragment
            transaction.replace(
                binding.container.id,
                SecondFragment.newInstance(num_two.toString(), "Called From MainFrag")
            )
            num_two++
            2
        }
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }

    /**
     * This the callback interface for FirstFragment.  We are getting string of data
     * which we will then send the SecondFragment.
     */
    override fun onFragmentInteraction1(Data: String) {

        //now change to the SecondFragment, pressing the back button should go to main fragment.
        val transaction = fragmentManager.beginTransaction()
        //remove firstfragment from the stack and replace it with two.
        transaction.replace(binding.container.id, SecondFragment.newInstance(num_two.toString(), Data))
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
        num_two++
    }

    /**
     * This the callback interface for SecondFragment.  We are getting string of data
     * which we will then send the FirstFragment.
     */
    override fun onFragmentInteraction2(Data: String) {

        //now change to the FirstFragment, pressing the back button should go to main fragment.
        val transaction = fragmentManager.beginTransaction()

        //remove Secondfragment from the stack and replace it with one.
        transaction.replace(binding.container.id, FirstFragment.newInstance(num_one.toString(), Data))
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
        num_one++
    }
}
