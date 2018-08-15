package edu.cs4730.intentDemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String value1 = extras.getString("key1");
        String value2 = extras.getString("key2");
        if (value1 != null && value2 != null) {
            EditText text1 = findViewById(R.id.EditText01);
            EditText text2 = findViewById(R.id.EditText02);
            text1.setText(value1);
            text2.setText(value2);
        }
    }

    public void onClick(View view) {
        finish();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("returnKey1", "A2: some data");
        data.putExtra("returnKey2", "A2: more data");
        setResult(RESULT_OK, data);
        super.finish();
    }
}
