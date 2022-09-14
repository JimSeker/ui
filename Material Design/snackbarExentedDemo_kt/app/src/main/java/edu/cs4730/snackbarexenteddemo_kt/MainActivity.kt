package edu.cs4730.snackbarexenteddemo_kt

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * A simple example of how to make the snackbar show indefinitely and how to dismiss it as well.
 *
 * It uses a thread to so we can show it to the time period we want it to be shown for.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var mMessageSnackbar: Snackbar
    private lateinit var myButton: Button
    var myThread: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myButton = findViewById(R.id.mybutton)
        myButton.setOnClickListener {
            myThread = Thread(CountingThread())
            myThread!!.start()
        }
    }

    internal inner class CountingThread : Runnable {
        override fun run() {
            //start of thread, so message.
            runOnUiThread {
                mMessageSnackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    "indefinite snackbar...",
                    Snackbar.LENGTH_INDEFINITE
                )
                //give the snackbar some color, so it really obvious.
                mMessageSnackbar.view.setBackgroundColor(
                    resources.getColor(
                        R.color.colorPrimary,
                        applicationContext.theme
                    )
                )
                mMessageSnackbar.show()
            }
            // wait for a while.
            for (i in 0..49) {
                SystemClock.sleep(250)
            }

            // end of thread so remove message.
            runOnUiThread {
                mMessageSnackbar.dismiss()
            }
        }
    }
}
