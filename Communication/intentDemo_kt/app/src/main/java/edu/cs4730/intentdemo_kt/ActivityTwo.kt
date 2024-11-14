package edu.cs4730.intentdemo_kt

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cs4730.intentdemo_kt.databinding.TwoActivityBinding

/**
 *  simple example.  it display the data sent to it and returns a fixed set of data.
 */

class ActivityTwo : AppCompatActivity() {
    lateinit var binding: TwoActivityBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TwoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main2) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        val extras = intent.extras ?: return
        val value1 = extras.getString("key1")
        val value2 = extras.getString("key2")
        if (value1 != null && value2 != null) {
            binding.EditText01.setText(value1)
            binding.EditText02.setText(value2)
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