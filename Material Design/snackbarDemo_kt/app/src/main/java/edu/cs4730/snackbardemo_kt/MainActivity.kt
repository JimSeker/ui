package edu.cs4730.snackbardemo_kt

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import edu.cs4730.snackbardemo_kt.databinding.ActivityMainBinding


/**
 * A demo of the snackbar callbacks.  So you can tell if the user swiped it away, or other actions.
 * And yes, the irony of using a toast to show snackbar is not lost on me either.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", SBonClickListener).addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(snackbar: Snackbar, event: Int) {
                        super.onDismissed(snackbar, event)
                        when (event) {
                            DISMISS_EVENT_ACTION -> Toast.makeText(
                                baseContext, "Dismiss from onclick", Toast.LENGTH_SHORT
                            ).show()

                            DISMISS_EVENT_SWIPE -> Toast.makeText(
                                baseContext, "Dismiss from user swipe", Toast.LENGTH_SHORT
                            ).show()

                            DISMISS_EVENT_TIMEOUT -> Toast.makeText(
                                baseContext, "Dismiss from timeout", Toast.LENGTH_SHORT
                            ).show()

                            DISMISS_EVENT_CONSECUTIVE -> Toast.makeText(
                                baseContext,
                                "Dismiss from another snackbar showing",
                                Toast.LENGTH_SHORT
                            ).show()

                            else -> Toast.makeText(
                                baseContext, "Dismiss for other reason.", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }).show()
        }
    }

    var SBonClickListener = View.OnClickListener {
        Toast.makeText(baseContext, "You clicked Action", Toast.LENGTH_SHORT).show()
    }
}
