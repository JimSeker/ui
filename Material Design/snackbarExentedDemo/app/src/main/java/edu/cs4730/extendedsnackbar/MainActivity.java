package edu.cs4730.extendedsnackbar;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;

/*
 * A simple example of how to make the snackbar show indefinitely and how to dismiss it as well.
 *
 * It uses a thread to so we can show it to the time period we want it to be shown for.
 *
 */

public class MainActivity extends AppCompatActivity {

  private Snackbar mMessageSnackbar = null;
  private Button myButton;
  Thread myThread;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    myButton = (Button) findViewById(R.id.mybutton);
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
              MainActivity.this.findViewById(android.R.id.content),
              "indefinite snackbar...", Snackbar.LENGTH_INDEFINITE);
          mMessageSnackbar.getView().setBackgroundColor(0xbf323232);
          mMessageSnackbar.show();
        }
      });
      // wait for a while.
      for (int i = 0; i < 100; i++) {
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
