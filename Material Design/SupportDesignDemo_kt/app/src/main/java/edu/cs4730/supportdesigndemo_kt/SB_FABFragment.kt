package edu.cs4730.supportdesigndemo_kt

import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.widget.Toast
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import edu.cs4730.supportdesigndemo_kt.databinding.FragmentSbfabBinding

/**
 * This example uses both a Floating Action button and a snackbar.
 * The fab is at the bttom right and it will move up and out of the way, so the snackbar will not
 * cover it up.  There is very little code in here to do it.  It's in the xml layout, android.support.design.widget.CoordinatorLayout
 * to do the work.  The snackbar is sent the coordinatorlayout, and the layout then "moves" the fab up (out of the way of snackbar)
 * so that there is no over lap.
 */
class SB_FABFragment : Fragment() {
    lateinit var binding: FragmentSbfabBinding

    var SBonClickListener =
        View.OnClickListener { Toast.makeText(requireContext(), "Yea!", Toast.LENGTH_SHORT).show() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSbfabBinding.inflate(inflater, container, false)

        binding.button02.setOnClickListener {
            Snackbar.make(binding.coordinatorlayout1, "Did the FAB move?", Snackbar.LENGTH_LONG)
                .setAction("Yes?", SBonClickListener) //this line is optional!
                .show()
        }
        binding.fab.setOnClickListener {
            Snackbar.make(
                binding.coordinatorlayout1, "I knew you had to click the FAB.", Snackbar.LENGTH_LONG
            ).show()
        }
        return binding.root
    }
}