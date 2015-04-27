package edu.cs4730.mvcdemo;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

	//model data, myColors holds all the information.
	ColorList myColors = new ColorList();
	
	//ImageView, which also needs a bitmap and Canvas to draw display the colors
	ImageView myPic;
	Bitmap theColor;
	Canvas theColorc;
	// The rest of the layout variables, buttons and text field.
	Button Next, Prev;
	TextView colorName;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//setup all the listeners and variables for the layout
		colorName = (TextView) findViewById(R.id.Name);
		
		//setup the picture stuff.
		myPic = (ImageView) findViewById(R.id.imageView1);
		//to draw the color with a bitmap and the canvas to draw on
		theColor = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
		theColorc = new Canvas(theColor);
		
		//buttons and listeners
		Next = (Button) findViewById(R.id.Next);
		Next.setOnClickListener(this);
		Prev = (Button) findViewById(R.id.Prev);
		Prev.setOnClickListener(this);
		Prev.setEnabled(false);  //can't go back yet.
		
		//setup first list
		colorName.setText(myColors.getName());
		createImage();
	}

	/*
	 * Simple methods to draw the color in the canvas (declared in oncreate) and put it in the imageview.
	 */
	void createImage() {
		//drawColor fills the image with that color.
		theColorc.drawColor(myColors.getNum());
		myPic.setImageBitmap(theColor);
	}

	/*
	 * (non-Javadoc)
	 * see android.view.View.OnClickListener#onClick(android.view.View)
	 * 
	 * Deals with the two buttons, next and previous.  also disables buttons when not necessary.
	 */
	@Override
	public void onClick(View v) {
		if (v == Next) {  //Next button
		  myColors.next();
		  colorName.setText(myColors.getName());
		  createImage();
		} else { //has to be Prev 
		  myColors.prev();
		  colorName.setText(myColors.getName());
		  createImage();	  
		}
		//set the buttons correctly.
		Next.setEnabled(!myColors.isLast());  //turn off when this is the last entry.
		Prev.setEnabled(!myColors.isFirst());  //turn off when this is the first entry
	}
}
