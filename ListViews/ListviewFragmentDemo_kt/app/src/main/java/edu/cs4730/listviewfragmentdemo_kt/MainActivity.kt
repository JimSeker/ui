package edu.cs4730.listviewfragmentdemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cs4730.listviewfragmentdemo_kt.databinding.ActivityMainBinding

/**
 * This is an example using the listview in a fragment.  There is very little code here that is not default
 * except the callbacks for the fragment named titlefrag.  There is a layout and layout-land for this
 * so the code also decides if it needs to display a fragment or if it is already showing.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */

class MainActivity : AppCompatActivity(), titlefrag.OnFragmentInteractionListener {
    var TwoPane = false

    lateinit var myTextFrag: textFrag
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        if (binding.container == null) {
            //landscape or large mode. both fragments will be displayed on the screen.
            // nothing to do, since it already showing.
            TwoPane = true
            myTextFrag = supportFragmentManager.findFragmentById(R.id.frag_text) as textFrag
        } else {
            //portrait or small screen.  the container exists.
            TwoPane = false
            //add the title fragment.
            supportFragmentManager.beginTransaction().add(R.id.container, titlefrag()).commit()
        }
    }


    override fun onItemSelected(id: Int) {
        if (TwoPane) {
            //already showing, so just update it.
            myTextFrag.setText(id)
        } else {
            //get an new instance of the fragment with the correct data.
            myTextFrag = textFrag.newInstance(id)
            val transaction = supportFragmentManager.beginTransaction()
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.container, myTextFrag, "second")
            transaction.addToBackStack(null)
            // Commit the transaction
            transaction.commit()
        }
    }
}
