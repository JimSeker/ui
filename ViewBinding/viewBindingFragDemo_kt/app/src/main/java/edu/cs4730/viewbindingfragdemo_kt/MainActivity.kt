package edu.cs4730.viewbindingfragdemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
class MainActivity : AppCompatActivity(), OneFragment.OnFragmentInteractionListener1 {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, OneFragment()).commit()
    }

    override fun onFragmentInteraction1(Data: String?) {
        supportFragmentManager.beginTransaction().replace(R.id.container, TwoFragment())
            .addToBackStack(null).commit()
    }
}