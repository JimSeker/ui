package edu.cs4730.autofilldemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.autofill.HintConstants;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.cs4730.autofilldemo.databinding.ActivityMainBinding;

/**
 * Teaching Example: AndroidX Autofill Integration with ViewBinding
 * <p>
 * This Activity demonstrates how to configure and interact with Android's Autofill Framework
 * using the AndroidX Autofill library ({@link HintConstants})
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AutofillDemo";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the ViewBinding layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle window insets for edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configure Autofill Hints programmatically (demonstration of AndroidX HintConstants)
        // 1. Username Field -> "username" (HintConstants.AUTOFILL_HINT_USERNAME)
        binding.etUsername.setAutofillHints(HintConstants.AUTOFILL_HINT_USERNAME);

        // 2. Password Field -> "password" (HintConstants.AUTOFILL_HINT_PASSWORD)
        binding.etPassword.setAutofillHints(HintConstants.AUTOFILL_HINT_PASSWORD);

        // 3. Email Address Field -> "emailAddress" (HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS)
        binding.etEmail.setAutofillHints(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS);

        // 4. Person Name Field -> "personName" (HintConstants.AUTOFILL_HINT_PERSON_NAME)
        binding.etPersonName.setAutofillHints(HintConstants.AUTOFILL_HINT_PERSON_NAME);

        // 5. Phone Number Field -> "phoneNumber" (HintConstants.AUTOFILL_HINT_PHONE_NUMBER)
        binding.etPhoneNumber.setAutofillHints(HintConstants.AUTOFILL_HINT_PHONE_NUMBER);

        // 6. Postal Address Field -> "postalAddress" (HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS)
        binding.etPostalAddress.setAutofillHints(HintConstants.AUTOFILL_HINT_POSTAL_ADDRESS);

        // 7.  Note for the second postal file address, we are NOT providing the hints to it, so it should not auto fill.
    }


}