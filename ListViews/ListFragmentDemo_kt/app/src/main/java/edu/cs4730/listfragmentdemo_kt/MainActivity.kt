package edu.cs4730.listfragmentdemo_kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * This is an example using the listfragment.  There is very little code here that is not default
 * except the callbacks for the listfragment named titlefrag.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */
class MainActivity : AppCompatActivity(), titlefrag.OnFragmentInteractionListener {
    lateinit var myTextFrag: textFrag
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the textFrag from the support manager;
        myTextFrag = supportFragmentManager.findFragmentById(R.id.frag_text) as textFrag
    }

    override fun onItemSelected(id: Int) {
        //use the setter in textFrag to change the text.
        myTextFrag.setText(id)
    }
}