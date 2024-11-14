package edu.cs4730.listviewfragmentdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import edu.cs4730.listviewfragmentdemo.databinding.ActivityMainBinding;

/**
 * This is an example using the listview in a fragment.  There is very little code here that is not default
 * except the callbacks for the fragment named titlefrag.  There is a layout and layout-land for this
 * so the code also decides if it needs to display a fragment or if it is already showing.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */

public class MainActivity extends AppCompatActivity implements titlefrag.OnFragmentInteractionListener {
    boolean TwoPane = false;
    ActivityMainBinding binding;
    textFrag myTextFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        if (binding.container == null) {
            //landscape or large mode. both fragments will be displayed on the screen.
            // nothing to do, since it already showing.
            TwoPane = true;
            myTextFrag = (textFrag) getSupportFragmentManager().findFragmentById(R.id.frag_text);
        } else {
            //portrait or small screen.  the container exists.
            TwoPane = false;
            //add the title fragment.
            getSupportFragmentManager().beginTransaction()
                    .add(binding.container.getId(), new titlefrag())
                    .commit();
        }
    }


    @Override
    public void onItemSelected(int id) {
        if (TwoPane) {
            //already showing, so just update it.
            myTextFrag.setText(id);
        } else {
            //get an new instance of the fragment with the correct data.
            myTextFrag = textFrag.newInstance(id);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(binding.container.getId(), myTextFrag, "second");
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }
    }
}
