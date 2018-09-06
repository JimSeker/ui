package edu.cs4730.listfragmentdemo;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

/*
 * This is an example using the listfragment.  There is very little code here that is not default
 * except the callbacks for the listfragment named titlefrag.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */

public class MainActivity extends AppCompatActivity implements titlefrag.OnFragmentInteractionListener {

    textFrag myTextFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the textFrag from the support manager;
        myTextFrag = (textFrag) getSupportFragmentManager().findFragmentById(R.id.frag_text);
    }

    @Override
    public void onItemSelected(int id) {
        //use the setter in textFrag to change the text.
        myTextFrag.setText(id);
    }
}
