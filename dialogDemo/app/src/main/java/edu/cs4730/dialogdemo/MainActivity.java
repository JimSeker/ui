package edu.cs4730.dialogdemo;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    findViewById(R.id.btn_old).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					startActivity(new Intent(getApplicationContext(), dialogs233Activity.class));
				}
	        }); 
       findViewById(R.id.btn_frag).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), FragmentDialogActivity.class));
			}
        });
       findViewById(R.id.btn_alert).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), DialFragActivity.class));
			}
        }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
