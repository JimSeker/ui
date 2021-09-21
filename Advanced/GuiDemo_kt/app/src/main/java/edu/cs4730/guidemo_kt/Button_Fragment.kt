package edu.cs4730.guidemo_kt

import android.content.Context
import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class Button_Fragment : Fragment(), View.OnClickListener {
    var TAG = "Button_Fragment"
    lateinit var myContext: Context
    lateinit var output: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myView = inflater.inflate(R.layout.button_fragment, container, false)
        output = myView.findViewById(R.id.output)

        //setup the listeners.  Each one setup a different way.
        //"standard" way
        val btn1 = myView.findViewById<Button>(R.id.button01)
        btn1.setOnClickListener { output.setText("Output:\nButton1") }

        //using the implements methods, ie this
        val btn2 = myView.findViewById<Button>(R.id.button02)
        btn2.setOnClickListener(this)

        //shortest version, no variable created.
        myView.findViewById<View>(R.id.button03).setOnClickListener(this)

        //note setting the listener in the xml android:onclick= will call the MainActivity, not the fragment!
        return myView
    }

    /*
     * This on is the for the implements View.OnClickListener
     *
     * (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    override fun onClick(v: View) {
        if (v.id == R.id.button02) {  //it's button 2
            Toast.makeText(myContext, "Button 2 was clicked", Toast.LENGTH_SHORT).show()
        } else if (v.id == R.id.button03) {  //it's button 3
            output!!.text = "Output:\nButton3"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
        Log.d(TAG, "onAttach")
    }
}