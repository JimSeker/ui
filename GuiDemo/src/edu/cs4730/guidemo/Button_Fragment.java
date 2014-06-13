package edu.cs4730.guidemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Button_Fragment extends Fragment implements View.OnClickListener {

    String TAG = "Button_Fragment";
    Context myContext;
	TextView output;
    
	public Button_Fragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.button_fragment, container, false);
		
    
		output = (TextView) myView.findViewById(R.id.output);
	    
		//setup the listeners.  Each one setup a different way.
		//"standard" way
		Button btn1 = (Button)myView.findViewById(R.id.button01);
        btn1.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				output.setText("Output:\nButton1");
			}
        });
        
        //using the implements methods, ie this
		Button btn2 = (Button)myView.findViewById(R.id.button02);
        btn2.setOnClickListener(this);
        
        //shortest version, no variable created.
		myView.findViewById(R.id.button03).setOnClickListener(this);
		
		//note setting the listener in the xml android:onclick= will call the MainActivity, not the fragment!
		
		return myView;
		
		
	}

	/* 
	 * This on is the for the implements View.OnClickListener
	 * 
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.button02) {  //it's button 2
			Toast.makeText( myContext, "Button 2 was clicked", Toast.LENGTH_SHORT).show();
		
		} else if (v.getId() == R.id.button03) {  //it's button 3
			output.setText("Output:\nButton3");
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		myContext = activity.getApplicationContext();  //needed for toast.
		Log.d(TAG,"onAttach");
	}

	
}
