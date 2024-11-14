package edu.cs4730.listfragmentdemo_kt

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cs4730.listfragmentdemo_kt.databinding.ActivityMainBinding

/**
 * This is an example using the listfragment.  There is very little code here that is not default
 * except the callbacks for the listfragment named titlefrag.
 *
 * see the two fragments textFrag and titlefrag for the bulk of the code.
 */
class MainActivity : AppCompatActivity(), titlefrag.OnFragmentInteractionListener {
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
        //get the textFrag from the support manager;
        myTextFrag = supportFragmentManager.findFragmentById(R.id.frag_text) as textFrag
    }

    override fun onItemSelected(id: Int) {
        //use the setter in textFrag to change the text.
        myTextFrag.setText(id)
    }
}