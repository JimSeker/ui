package edu.cs4730.simplefragcomdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

/**
 * simple example of callbacks and two fragments.
 *
 * When the device is in portrait, it displays the main fragment with a button.  When clicked it
 * then displays and updates the info fragment.
 *
 * When the device is in landscape, it simply updates the info fragment, since it is already showing.
 *
 * Note, when the device changes between landscape and portrait, the num of clicks is reset.
 * No attempt at storing the data was made.  see save data repo, for how this might be done.
 */
class MainActivity : AppCompatActivity(), MainFragment.OnFragmentInteractionListener {
    lateinit var main: MainFragment
    lateinit var info: InfoFragment
    var twopane = false
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager

        //setup the correct layout, based on if container (portrait) exists.
        if (findViewById<View?>(R.id.container) == null) { //two pane
            twopane = true
            //since the fragments are already showing and exist, just go get them.
            main = fragmentManager.findFragmentById(R.id.frag_main) as MainFragment
            info = fragmentManager.findFragmentById(R.id.frag_info) as InfoFragment
        } else {
            twopane = false
            //just a framelayout, so construct the fragments and then display main.
            main = MainFragment()
            info = InfoFragment()
            fragmentManager.beginTransaction()
                .add(R.id.container, main)
                .commit()
        }
    }

    override fun onFragmentInteraction(Num: Int) {
        if (twopane) {
            //Log.v("MainActivity", "twopane click");
            info.update(Num) //info fragment is already showing, just update the value.
        } else {
            // Log.v("MainActivity", "click");
            //now update the info fragment.
            info.update(Num)
            //so we need to display the fragment info and then update it as well.
            val transaction = fragmentManager.beginTransaction()
            //now setup to replace the current fragment.
            transaction.replace(R.id.container, info)
            // and add the transaction to the back stack so the user can navigate back
            transaction.addToBackStack(null)
            // Commit the transaction
            transaction.commit()
        }
    }
}
