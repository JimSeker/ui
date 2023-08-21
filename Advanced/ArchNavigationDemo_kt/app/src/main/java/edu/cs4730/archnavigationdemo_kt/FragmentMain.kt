package edu.cs4730.archnavigationdemo_kt

import androidx.navigation.Navigation.createNavigateOnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import edu.cs4730.archnavigationdemo_kt.databinding.FragmentMainBinding

/**
 * This fragment is the "main" fragment that is shown first.
 */
class FragmentMain : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)

        //add transaction for the button or the convenience method below (uncommented).
        //binding.button.setOnClickListener { view -> findNavController(view).navigate(R.id.action_fragmentMain_to_fragment_two) }
        //or convenience method
        binding.button.setOnClickListener(
            createNavigateOnClickListener(
                R.id.action_fragmentMain_to_fragment_two, null
            )
        )
        return binding.root
    }
}