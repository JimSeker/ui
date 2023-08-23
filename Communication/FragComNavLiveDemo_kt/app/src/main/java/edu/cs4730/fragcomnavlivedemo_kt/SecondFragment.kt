package edu.cs4730.fragcomnavlivedemo_kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import edu.cs4730.fragcomnavlivedemo_kt.databinding.FragmentSecondBinding

/**
 *
 */
class SecondFragment : Fragment() {
    lateinit var binding: FragmentSecondBinding
    lateinit var mViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(DataViewModel::class.java)
        mViewModel.gettwoLD().observe(viewLifecycleOwner) { v -> binding.tv1.text = "Parameter1: $v" }
        mViewModel.itemLD.observe(viewLifecycleOwner) { s -> binding.tv2.text = "Parameter1: $s" }
        binding.btn1.setOnClickListener(View.OnClickListener { v -> //this is calling the interface, which call into the activity, so it
            mViewModel.incr_One()
            mViewModel.setItem("Called by SecondFragment")
            findNavController(v).navigate(R.id.action_second_to_first)
        })
        return binding.root
    }
}