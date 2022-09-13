package edu.cs4730.intentdemo_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.view.View

/**
 *  simple example.  it display the data sent to it and returns a fixed set of data.
 */

class ActivityTwo : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.two_activity)
        val extras = intent.extras ?: return
        val value1 = extras.getString("key1")
        val value2 = extras.getString("key2")
        if (value1 != null && value2 != null) {
            val text1 = findViewById<EditText>(R.id.EditText01)
            val text2 = findViewById<EditText>(R.id.EditText02)
            text1.setText(value1)
            text2.setText(value2)
        }
    }

    fun onClick(view: View?) {
        finish()
    }

    override fun finish() {
        val data = Intent()
        data.putExtra("returnKey1", "A2: some data")
        data.putExtra("returnKey2", "A2: more data")
        setResult(RESULT_OK, data)
        super.finish()
    }
}