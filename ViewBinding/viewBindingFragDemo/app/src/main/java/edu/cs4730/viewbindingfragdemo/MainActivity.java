package edu.cs4730.viewbindingfragdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * a very simple example of using viewbinding with fragment.  The variables names are the id names in the xml.
 * the file, is the camel case version of the xml file name (only letters, cap'd for each word) with the
 * Binding appended to the name.  Note, this is NOT dataBinding.
 *
 * viewbinding allows you to skip all the findviewbyid calls and you should not have any null pointer issues
 * since the binding variable is used for everything.
 * note it must be turned on in the build.gradle file.
 *
 * In this example see the fragments.  nothing here to uses viewBinding.
 */

public class MainActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new OneFragment()).commit();

    }

    @Override
    public void onFragmentInteraction1(String Data) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TwoFragment())
            .addToBackStack(null).commit();
    }
}