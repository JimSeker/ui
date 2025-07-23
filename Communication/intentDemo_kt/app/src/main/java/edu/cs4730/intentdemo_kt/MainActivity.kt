package edu.cs4730.intentdemo_kt

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cs4730.intentdemo_kt.databinding.ActivityMainBinding
import androidx.core.net.toUri

/**
 * Example of how to call varying system intents, such maps, phone, etc.
 * also how to call another activity with data and return data as well.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // for checking permissions.
    lateinit var cameraRpl: ActivityResultLauncher<String>
    lateinit var phoneRpl: ActivityResultLauncher<String>
    lateinit var contactRpl: ActivityResultLauncher<String>
    lateinit var binding: ActivityMainBinding
    var TAG = "MainActivity"
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }
        //For the camera permissions
        cameraRpl = registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                makeCall() //we have permissions now.
            } else {
                Toast.makeText(
                    applicationContext,
                    "Unable to complete phone action, because I need permissions",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        //for the phone permissions
        phoneRpl = registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                takePic() //we have permissions now.
            } else {
                Toast.makeText(
                    applicationContext,
                    "Unable to complete camera action, because I need permissions",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        //for the Contact permissions
        contactRpl = registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                pickContact() //we have permissions now.
            } else {
                Toast.makeText(
                    applicationContext,
                    "Unable to complete Contact action, because I need permissions",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        //set all the listeners for the buttons.
        binding.callbrowser.setOnClickListener(this)
        binding.takepic.setOnClickListener(this)
        binding.pickcontact.setOnClickListener(this)
        binding.activitytwo.setOnClickListener(this)
        binding.showmap.setOnClickListener(this)
        binding.searchmap.setOnClickListener(this)
        binding.callnumber.setOnClickListener(this)
        binding.dialnumber.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent: Intent
        if (view === binding.callbrowser) {
            intent = Intent(Intent.ACTION_VIEW, "http://www.eecs.uwyo.edu".toUri())
            startActivity(intent)
        } else if (view === binding.callnumber) {
            makeCall() //needs permissions, so moved to a method.
        } else if (view === binding.dialnumber) {
            intent = Intent(Intent.ACTION_DIAL, "tel:(307)555555".toUri())
            startActivity(intent)
        } else if (view === binding.showmap) {
            intent = Intent(Intent.ACTION_VIEW, "geo:41.312927,105.587251?z=19".toUri())
            startActivity(intent)
        } else if (view === binding.searchmap) {
            intent = Intent(Intent.ACTION_VIEW, "geo:0,0?q=query".toUri())
            startActivity(intent)
        } else if (view === binding.takepic) {
            takePic()
        } else if (view === binding.activitytwo) {
            intent = Intent(this, ActivityTwo::class.java)
            intent.putExtra("key1", "Some Data")
            intent.putExtra("key2", "More Data")
            // Set the request code to any code you like, you can identify the
            // callback via this code
            act2ActivityResultLauncher.launch(intent)
        } else if (view === binding.pickcontact) {
            pickContact()
        }
    }

    //These the launchers for results.
    var cameraActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val data = result.data
            val extras = data!!.extras
            if (extras != null) {
                Toast.makeText(applicationContext, "There is a picture", Toast.LENGTH_LONG).show()
                //See PicCaptureIntent in AudioVideo repo for how deal with this and take pictures.
            } else {
                Toast.makeText(applicationContext, "error in returned data.", Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            Toast.makeText(applicationContext, "Camera action canceled", Toast.LENGTH_SHORT).show()
        }
    }
    var contactActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val data = result.data
            val c = contentResolver.query(data!!.data!!, null, null, null, null)
            c!!.moveToFirst() //I know the query worked, since we just picked it, otherwise, should be an if statement.
            val name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
            Toast.makeText(applicationContext, "You Picked: $name", Toast.LENGTH_LONG).show()
            c.close()
        } else {
            Toast.makeText(applicationContext, "pick contact action canceled", Toast.LENGTH_SHORT)
                .show()
        }
    }
    var act2ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val data = result.data
            if (data!!.hasExtra("returnKey1")) {
                Toast.makeText(
                    applicationContext, data.extras!!.getString("returnKey1"), Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(applicationContext, "ActivityTwo action canceled", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //For these three intents, we need permissions, so check permission first, then launch the intents.
    fun makeCall() {

        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions")
            phoneRpl.launch(Manifest.permission.CALL_PHONE)
            return
        }
        val intent = Intent(Intent.ACTION_CALL, "tel:3075555555".toUri())
        startActivity(intent)
    }

    fun takePic() {

        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions")
            cameraRpl.launch(Manifest.permission.CAMERA)
            return
        }
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraActivityResultLauncher.launch(intent)
    }

    fun pickContact() {
        //make sure I permissions first.
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions")
            contactRpl.launch(Manifest.permission.READ_CONTACTS)
            return
        }
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        contactActivityResultLauncher.launch(intent)
    }
}