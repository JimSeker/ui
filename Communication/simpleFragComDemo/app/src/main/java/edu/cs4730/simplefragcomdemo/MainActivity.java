package edu.cs4730.simplefragcomdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.cs4730.simplefragcomdemo.databinding.ActivityMainBinding;

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

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {
    MainFragment main;
    InfoFragment info;
    Boolean twopane = false;
    FragmentManager fragmentManager;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentManager = getSupportFragmentManager();

        //setup the correct layout, based on if container (portrait) exists.
        if (binding.container == null) { //two pane
            twopane = true;
            //since the fragments are already showing and exist, just go get them.
            main = (MainFragment) fragmentManager.findFragmentById(R.id.frag_main);
            info = (InfoFragment) fragmentManager.findFragmentById(R.id.frag_info);
        } else {
            twopane = false;
            //just a framelayout, so construct the fragments and then display main.
            main = new MainFragment();
            info = new InfoFragment();
            fragmentManager.beginTransaction()
                .add(binding.container.getId(), main)
                .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Integer Num) {

        if (twopane) {
            // Log.v("MainActivity", "twopane click");
            info.update(Num);  //info fragment is already showing, just update the value.
        } else {
            // Log.v("MainActivity", "click");
            //now update the info fragment.
            info.update(Num);
            //so we need to display the fragment info and then update it as well.
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //now setup to replace the current fragment.
            transaction.replace(binding.container.getId(), info);
            // and add the transaction to the back stack so the user can navigate back
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        }
    }
}
