package edu.cs4730.intentDemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class IntentDemoActivity extends AppCompatActivity {

    // for checking permissions.
    ActivityResultLauncher<String> cameraRpl, phoneRpl;

    String TAG = "IntentDemoActivity";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Note, the "listeners" are setup in the main.xml file, in android:onClick="callIntent"
        //callIntent must take a parameter View v, like a standard OnClickListner.

        //For the camera permissions
        cameraRpl = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        makeCall();  //we have permissions now.
                    } else {
                        Toast.makeText(getApplicationContext(), "Unable to complete phone action, because I need permissions", Toast.LENGTH_LONG).show();
                    }
                }
            });

        //for the phone permissions
        phoneRpl = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        takePic();  //we have permissions now.
                    } else {
                        Toast.makeText(getApplicationContext(), "Unable to complete camera action, because I need permissions", Toast.LENGTH_LONG).show();
                    }
                }
            });

        //Also note, the map may not always work in the simulator and
        //sometimes the camera crashes, and I can't get the contact editor to work at all in the simulator. 

    }

    @SuppressLint("NonConstantResourceId")
    public void callIntent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.Button01:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cs.uwyo.edu"));
                startActivity(intent);
                break;
            case R.id.Button02:
                makeCall();  //needs permissions, so moved to a method.
                break;
            case R.id.Button03:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(307)555555"));
                startActivity(intent);
                break;
            case R.id.Button04:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.312927,105.587251?z=19"));
                startActivity(intent);
                break;
            case R.id.Button05:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=query"));
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
                act2ActivityResultLauncher.launch(intent);
                break;
            case R.id.Button10:
                intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/people/"));
                contactActivityResultLauncher.launch(intent);
                break;
            default:
                break;
        }
    }

    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Uri info = data.getData();
                    if (info != null) {
                        Toast.makeText(getApplicationContext(), info.toString(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "error in returned data.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Camera action canceled", Toast.LENGTH_SHORT).show();

                }
            }
        });
    ActivityResultLauncher<Intent> contactActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Cursor c = getContentResolver().query(data.getData(), null, null, null, null);
                    c.moveToFirst();  //I know the query worked, since we just picked it, otherwise, should be an if statement.
                    String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Toast.makeText(getApplicationContext(), "You Picked: " + name, Toast.LENGTH_LONG).show();
                    c.close();
                } else {
                    Toast.makeText(getApplicationContext(), "pick contact action canceled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    ActivityResultLauncher<Intent> act2ActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    if (data.hasExtra("returnKey1")) {
                        Toast.makeText(getApplicationContext(), data.getExtras().getString("returnKey1"),
                            Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ActivityTwo action canceled", Toast.LENGTH_SHORT).show();
                }
            }
        });


    public void makeCall() {

        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions");
            phoneRpl.launch(Manifest.permission.CALL_PHONE);
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
            cameraRpl.launch(Manifest.permission.CAMERA);
            return;
        }
        Intent intent;
        intent = new Intent("android.media.action.IMAGE_CAPTURE");
        cameraActivityResultLauncher.launch(intent);
    }

}