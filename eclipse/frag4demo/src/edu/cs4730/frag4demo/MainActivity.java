package edu.cs4730.frag4demo;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;


public class MainActivity extends FragmentActivity implements OneFragment.OnFragmentInteractionListener {


	//used to move the fragments.
	FragmentManager fragmentManager;
	int num_two =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		fragmentManager = getSupportFragmentManager();

		if (savedInstanceState == null) {
			fragmentManager.beginTransaction()
			.add(R.id.container, OneFragment.newInstance("First time", "Created by MainActivity")).commit();
		}
	}



	/*
	 * This is the callback for MainFragment.  It takes as which number, which well is the fragment
	 * to launch.
	 */
	@Override
	public void onFragmentInteraction() {

		//going to change via the transcation manager, instead of just a simple replace.
		FragmentTransaction transaction =fragmentManager.beginTransaction();

		//add twofragment to the top.
		transaction.replace(R.id.container,  TwoFragment.newInstance(String.valueOf(num_two), "Called From OneFrag"));	

		//So the back button works, add the transaction to the back stack so the user can navigate back via the back button
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();
		num_two++;
	}

}
