package edu.cs4730.extendedsnackbar;

import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import edu.cs4730.extendedsnackbar.databinding.ActivityMainBinding;

/**
 * A simple example of how to make the snackbar show indefinitely and how to dismiss it as well.
 * <p>
 * It uses a thread to so we can show it to the time period we want it to be shown for.
 */

public class MainActivity extends AppCompatActivity {

    private Snackbar mMessageSnackbar;
    private ActivityMainBinding binding;
    Thread myThread;

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
        binding.mybutton.setOnClickListener(new View.OnClickListener() {
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
                    mMessageSnackbar = Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "indefinite snackbar...", Snackbar.LENGTH_INDEFINITE);
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
                }
            });
        }
    }


}
