package edu.cs4730.customdialogdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import edu.cs4730.customdialogdemo.databinding.ActivityMainBinding;
import edu.cs4730.customdialogdemo.databinding.LayoutCustomDialogBinding;

/**
 * simple dialog example to show how to create a custom dialog box with 2 EditText boxes.
 */

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog("Input");
            }
        });

    }

    /**
     * simple method to display data to the logger textview.
     */
    void displaylog(String item) {
        Log.v(TAG, item);
        binding.logger.append(item + "\n");
    }

    /**
     * We are going to add data
     * setup dialog to ask the user for the new item data or category.
     */
    public void showInputDialog(String title) {
        LayoutInflater inflater = LayoutInflater.from(this);
        LayoutCustomDialogBinding binding = LayoutCustomDialogBinding.inflate(inflater);

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog));
        builder.setView(binding.getRoot()).setTitle(title);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        displaylog("data is " + binding.etFirst.getText().toString() + " " + binding.etLast.getText().toString());
                        //Toast.makeText(getBaseContext(), binding.etFirst.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        displaylog("dialog canceled");
                        dialog.cancel();
                    }
                });
        //you can create the dialog or just use the now method in the builder.
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.show();
    }


}