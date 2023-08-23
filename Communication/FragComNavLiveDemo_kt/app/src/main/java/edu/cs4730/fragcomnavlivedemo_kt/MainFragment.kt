package edu.cs4730.fragcomnavlivedemo_kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import edu.cs4730.fragcomnavlivedemo_kt.databinding.FragmentMainBinding

/**
 */
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        //button to call firstfragment
        binding.button1.setOnClickListener { v ->
            //this is calling the navigation to call the action to change to first fragment.
            //can change to the first fragment and send a simple string as well.
            mViewModel.incr_One()
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_first)
        }
        //button to call secondfragment.
        binding.button2.setOnClickListener { v ->
            //this is calling the navigation to call the action to change to second fragment.
            mViewModel.incr_Two()
            mViewModel.setItem("Called From MainFrag")
            findNavController(v).navigate(R.id.action_main_to_second)
        }
        mViewModel.getoneLD()
            .observe(viewLifecycleOwner) { v -> binding.tv1.text = "Parameter1: $v" }
        mViewModel.gettwoLD()
            .observe(viewLifecycleOwner) { v -> binding.tv2.text = "Parameter1: $v" }
        return binding.root
    }
}