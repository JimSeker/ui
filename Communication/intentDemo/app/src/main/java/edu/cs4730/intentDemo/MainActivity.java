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
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.cs4730.intentDemo.databinding.ActivityMainBinding;

/**
 * Example of how to call varying system intents, such maps, phone, etc.
 * also how to call another activity with data and return data as well.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // for checking permissions.
    ActivityResultLauncher<String> cameraRpl, phoneRpl, contactRpl;
    //intent launchers are declared below the onCreate method.
    ActivityMainBinding binding;
    String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
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
        //for the Contact permissions
        contactRpl = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean isGranted) {
                    if (isGranted) {
                        pickContact();  //we have permissions now.
                    } else {
                        Toast.makeText(getApplicationContext(), "Unable to complete Contact action, because I need permissions", Toast.LENGTH_LONG).show();
                    }
                }
            });

        //set all the listeners for the buttons.
        binding.callbrowser.setOnClickListener(this);
        binding.takepic.setOnClickListener(this);
        binding.pickcontact.setOnClickListener(this);
        binding.activitytwo.setOnClickListener(this);
        binding.showmap.setOnClickListener(this);
        binding.searchmap.setOnClickListener(this);
        binding.callnumber.setOnClickListener(this);
        binding.dialnumber.setOnClickListener(this);

    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        Intent intent;

        if (view == binding.callbrowser) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.eecs.uwyo.edu"));
            startActivity(intent);
        } else if (view == binding.callnumber) {
            makeCall();  //needs permissions, so moved to a method.
        } else if (view == binding.dialnumber) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(307)555555"));
            startActivity(intent);
        } else if (view == binding.showmap) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.312927,105.587251?z=19"));
            startActivity(intent);
        } else if (view == binding.searchmap) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=query"));
            startActivity(intent);
        } else if (view == binding.takepic) {
            takePic();
        } else if (view == binding.activitytwo) {
            intent = new Intent(this, ActivityTwo.class);
            intent.putExtra("key1", "Some Data");
            intent.putExtra("key2", "More Data");
            // Set the request code to any code you like, you can identify the
            // callback via this code
            act2ActivityResultLauncher.launch(intent);
        } else if (view == binding.pickcontact) {
            pickContact();
        }
    }

    //These the launchers for results.
    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Toast.makeText(getApplicationContext(),"There is a picture", Toast.LENGTH_LONG).show();
                        //See PicCaptureIntent in AudioVideo repo for how deal with this and take pictures.
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


    //For these three intents, we need permissions, so check permission first, then launch the intents.
    public void makeCall() {

        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions");
            phoneRpl.launch(Manifest.permission.CALL_PHONE);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:3075555555"));
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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraActivityResultLauncher.launch(intent);
    }

    public void pickContact() {
        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions");
            contactRpl.launch(Manifest.permission.READ_CONTACTS);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        contactActivityResultLauncher.launch(intent);
    }

}