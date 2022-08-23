package edu.cs4730.dialogdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * This is an example of how to use different dialogs
 *   A note, as of API 13 showDialog is a deprecated in favor of dialogFragments... which is what the other two are demonstrating.
 *   deprecated doesn't mean gone, so this example is still valid on anything before 13.  But you notice lots of notes from eclipse that 
 *   this is deprecated!
 */

public class dialogs233Activity extends AppCompatActivity {
	static final int DIALOG_TYPE_ID = 0;
	static final int DIALOG_GAMEOVER_ID = 1;
	static final int DIALOG_CUSTOM_ID = 2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogsoldactivity);
        findViewById(R.id.oldButton01).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_GAMEOVER_ID);
			}

        });
        findViewById(R.id.oldButton02).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {            
				showDialog(DIALOG_TYPE_ID);
			}
        });
        findViewById(R.id.oldButton03).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_CUSTOM_ID);
			}
        });
    }

  protected Dialog onCreateDialog(int id) {
    Dialog dialog = null;
    AlertDialog.Builder builder;
    switch (id) {
      case DIALOG_TYPE_ID:
        final String[] items = { "Remove Walls", "Add Walls",
            "Add/Remove Objects", "Add/Remove Score" };
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Type:");
        builder.setSingleChoiceItems(items, -1,
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
                if (item == 0) { // remove walls
                  Toast.makeText(getApplicationContext(), "Remove walls",
                      Toast.LENGTH_SHORT).show();
                } else if (item == 1) { // Add Walls
                  Toast.makeText(getApplicationContext(), "Add Walls",
                      Toast.LENGTH_SHORT).show();
                } else if (item == 2) { // Add Objects
                  Toast.makeText(getApplicationContext(), "Add Objects",
                      Toast.LENGTH_SHORT).show();
                } else if (item == 3) { // /Add Scores
                  Toast.makeText(getApplicationContext(), "Add Scores",
                      Toast.LENGTH_SHORT).show();
                }
              }
            });
        dialog = builder.create();

        break;
      case DIALOG_GAMEOVER_ID:
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialogs233Activity.this.finish();
              }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });
        dialog = builder.create();
        break;
      case DIALOG_CUSTOM_ID:
        final Dialog myDialog = new Dialog(this);

        myDialog.setContentView(R.layout.oldcustomdialog);
        myDialog.setTitle("Custom Dialog");

        TextView text = (TextView) myDialog.findViewById(R.id.text);
        text.setText("Hello, this is a custom dialog!");
        ImageView image = (ImageView) myDialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.pic);
        myDialog.setCancelable(true);
        myDialog.findViewById(R.id.Button01).setOnClickListener(
            new OnClickListener() {
              @Override
              public void onClick(View v) {
                myDialog.dismiss();
              }
            });
        return myDialog;
      default:
        dialog = null;
    }
    return dialog;
  }
}
