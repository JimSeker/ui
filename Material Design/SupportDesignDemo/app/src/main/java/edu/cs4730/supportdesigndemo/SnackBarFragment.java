package edu.cs4730.supportdesigndemo;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.cs4730.supportdesigndemo.databinding.FragmentSnackbarBinding;

/**
 * This is an example of how to use the snackbar.  Which is very similar to a toast with
 * two main differences.  first the user can swipe it away and second, you can have a button
 * as well via the setAction call.
 */
public class SnackBarFragment extends Fragment {

    FragmentSnackbarBinding binding;

    View.OnClickListener SBonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(requireActivity(), "You clicked undo", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSnackbarBinding.inflate(inflater, container, false);
        binding.button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This setups and show the snackbar message.
                Snackbar.make(binding.getRoot(), "Hi there?", Snackbar.LENGTH_LONG)  //or LENGTH_SHORT
                        .setAction("Undo?", SBonClickListener)  //this line is optional.
                        .show();
            }
        });
        return binding.getRoot();
    }
}
