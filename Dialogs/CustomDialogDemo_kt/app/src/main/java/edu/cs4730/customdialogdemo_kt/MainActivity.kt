package edu.cs4730.customdialogdemo_kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import edu.cs4730.customdialogdemo_kt.databinding.ActivityMainBinding
import edu.cs4730.customdialogdemo_kt.databinding.LayoutCustomDialogBinding

/**
 * simple dialog example to show how to create a custom dialog box with 2 EditText boxes.
 */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { showInputDialog("Input") }
    }


    /**
     * simple method to display data to the logger textview.
     */
    fun displaylog(item: String) {
        Log.v(TAG, item)
        binding.logger.append(item + "\n")
    }

    /**
     * We are going to add data
     * setup dialog to ask the user for the new item data or category.
     */
    fun showInputDialog(title: String?) {
        val inflater = LayoutInflater.from(this)
        val binding: LayoutCustomDialogBinding = LayoutCustomDialogBinding.inflate(inflater)
        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                this,
                androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog
            )
        )
        builder.setView(binding.root).setTitle(title)
        builder.setPositiveButton("Add") { dailog, id ->
            displaylog(
                "data is " + binding.etFirst.text.toString() +
                        " " + binding.etLast.text.toString()
            )
            //Toast.makeText(getBaseContext(), binding.etFirst.getText().toString(), Toast.LENGTH_LONG).show();
        }
            .setNegativeButton("Cancel") { dialog, id ->
                displaylog("dialog canceled")
                dialog.cancel()
            }
        //you can create the dialog or just use the now method in the builder.
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.show()
    }


}