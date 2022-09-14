package edu.cs4730.extendedsnackbar;

import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple example of how to make the snackbar show indefinitely and how to dismiss it as well.
 * <p>
 * It uses a thread to so we can show it to the time period we want it to be shown for.
 */

public class MainActivity extends AppCompatActivity {

    private Snackbar mMessageSnackbar = null;
    private Button myButton;
    Thread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.mybutton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread = new Thread(new CountingThread());
                myThread.start();
            }
        });

    }

    class CountingThread implements Runnable {

        @Override
        public void run() {
            //start of thread, so message.
            runOnUiThread(new Runnable() {  //we need to run this on the main ui thread to access the UI.
                @Override
                public void run() {
                    mMessageSnackbar = Snackbar.make(
                        MainActivity.this.findViewById(android.R.id.content), "indefinite snackbar...", Snackbar.LENGTH_INDEFINITE);
                    //give the snackbar some color, so it really obvious.
                    mMessageSnackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary, getApplicationContext().getTheme()));
                    mMessageSnackbar.show();
                }
            });
            // wait for a while.
            for (int i = 0; i < 50; i++) {
                SystemClock.sleep(250);
            }

            // end of thread so remove message.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMessageSnackbar.dismiss();
                    mMessageSnackbar = null;
                }
            });
        }
    }


}
