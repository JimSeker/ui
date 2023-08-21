package edu.cs4730.archnavigationdemo_kt

import androidx.navigation.Navigation.findNavController
import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import edu.cs4730.archnavigationdemo_kt.databinding.FragmentTwoBinding

/**
 * Second fragment to seen by the use.  This also shows how to pass data via a bundle or safe_args.
 */
class Fragment_two : Fragment() {
    lateinit var binding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        binding.twoBack.setOnClickListener { view ->
            findNavController(view).navigateUp() //go back!
        }
        //now for passing data.

        //add transaction for the button.
        binding.twoNext.setOnClickListener { view -> //get the "action" and add the parameters to it.  Then navigate to it.
            val action = Fragment_twoDirections.actionFragmentTwoToFragmentThree()
            action.message = binding.editText.text.toString()
            action.number = 3012
            findNavController(view).navigate(action)
        }

        //add transaction for the button.
        binding.twoNextBundle.setOnClickListener { view -> //this uses a bundle instead of safe args.  Also is a lot simpler.  No addition info in needed in nav.
            val bundle = Bundle()
            bundle.putString("message", binding.editText.text.toString())
            bundle.putInt("number", 3012)
            findNavController(view).navigate(R.id.action_two_to_bundle, bundle)
        }
        return binding.root
    }
}