package edu.cs4730.intentDemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class IntentDemoActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CAMERA = 10;
    private static final int REQUEST_CODE_ACT2 = 11;
    private static final int REQUEST_CODE_PICKCONTACT = 12;

    String TAG = "IntentDemoActivity";
    // for checking permissions.
    public static final int REQUEST_ACCESS_camera = 0;
    public static final int REQUEST_ACCESS_phone = 1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Note, the "listeners" are setup in the main.xml file, in android:onClick="callIntent"
        //callIntent must take a parameter View v, like a standard OnClickListner.

        //Also note, the map may not always work in the simulator and
        //sometimes the camera crashes, and I can't get the contact editor to work at all in the simulator. 

    }

    public void callIntent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.Button01:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.cs.uwyo.edu"));
                startActivity(intent);
                break;
            case R.id.Button02:
                makeCall();  //needs permissions, so moved to a method.
                break;
            case R.id.Button03:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(307)555555"));
                startActivity(intent);
                break;
            case R.id.Button04:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:41.312927,105.587251?z=19"));
                startActivity(intent);
                break;
            case R.id.Button05:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=query"));
                startActivity(intent);
                break;
            case R.id.Button06:
                takePic();
                break;
            case R.id.Button07:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
                break;
            case R.id.Button08:
                //This always force closes...
                intent = new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/1"));
                startActivity(intent);
                break;
            case R.id.Button09:
                intent = new Intent(this, ActivityTwo.class);
                intent.putExtra("key1", "Some Data");
                intent.putExtra("key2", "More Data");
                // Set the request code to any code you like, you can identify the
                // callback via this code
                startActivityForResult(intent, REQUEST_CODE_ACT2);
                break;
            case R.id.Button10:
                intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/people/"));
                startActivityForResult(intent, REQUEST_CODE_PICKCONTACT);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(this, "Request code is " + requestCode, Toast.LENGTH_LONG).show();
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (resultCode == Activity.RESULT_OK) { //get where the picture is stored and display info
                String result = data.toURI();
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Camera action canceled", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_ACT2) {
            if (resultCode == Activity.RESULT_OK) { //ActivityTwo finished correctly.
                if (data.hasExtra("returnKey1")) {
                    Toast.makeText(this, data.getExtras().getString("returnKey1"),
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "ActivityTwo action canceled", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_PICKCONTACT) {
            Toast.makeText(this, "Contact list at least", Toast.LENGTH_SHORT);
            if (resultCode == Activity.RESULT_OK) { //ActivityTwo finished correctly.
                //String result = data.getData().toString();
                //Get the contact's name:
                Cursor c = managedQuery(data.getData(), null, null, null, null);
                c.moveToFirst();  //I know the query worked, since we just picked it, otherwise, should be an if statement.
                String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                Toast.makeText(this, "You Picked: " + name, Toast.LENGTH_LONG).show();


                //startActivity(new Intent(Intent.ACTION_EDIT, data.getData()));
            } else {
                Toast.makeText(this, "ActivityTwo action canceled", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void makeCall() {

        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_ACCESS_phone);
            return;
        }
        Intent intent;
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:3075555555"));
        startActivity(intent);
    }

    public void takePic() {

        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_ACCESS_camera);
            return;
        }
        Intent intent;
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.v(TAG, "onRequest result called.");
        if (requestCode == REQUEST_ACCESS_phone) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall();  //we have permissions now.
            } else {
                Toast.makeText(this, "Unable to complete phone action, because I need premissions", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_ACCESS_camera) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePic();  //we have permissions now.
            } else {
                Toast.makeText(this, "Unable to complete camera action, because I need premissions", Toast.LENGTH_LONG).show();
            }
        }
    }
}