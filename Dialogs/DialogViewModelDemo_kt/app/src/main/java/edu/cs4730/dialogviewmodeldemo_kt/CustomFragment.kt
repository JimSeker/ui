package edu.cs4730.dialogviewmodeldemo_kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.cs4730.dialogviewmodeldemo_kt.databinding.FragmentCustomBinding
import edu.cs4730.dialogviewmodeldemo_kt.databinding.LayoutCustomDialogBinding

/**
 * This shows how to use the dialog that have been extended.  Note the listeners are implemented
 * in the mainActivity and call the displaylog method at the bottom of this fragment.
 * This may not be the way you want to implements your listeners.  It was easier for communication of
 * the fragments.  Sometimes you want to the listeners om the dialog itself, if you have access to the
 * necessary functions and data.
 */
class CustomFragment : Fragment() {
    var TAG = "CustomFragment"
    lateinit var mViewModel: DataViewModel
    lateinit var binding: FragmentCustomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCustomBinding.inflate(inflater, container, false)

        //note, I'm not setting up an observer here, but you could do here instead, but mainactivty "could" change the fragment
        //based on data, so for this example it's in main activity, but honesty doesn't need to be.
        mViewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        binding.btnAlert1.setOnClickListener {
            val newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_TYPE_ID)
            newDialog.show(requireActivity().supportFragmentManager, "myDialog")
        }
        binding.btnAlert2.setOnClickListener {
            val newDialog = myDialogFragment.newInstance(myDialogFragment.DIALOG_GAMEOVER_ID)
            newDialog.show(requireActivity().supportFragmentManager, "myDialog")
        }
        binding.btnAlert3.setOnClickListener {
            val newDialog =
                myAlertDialogFragment.newInstance(R.string.alert_dialog_two_buttons_title)
            newDialog.show(requireActivity().supportFragmentManager, "myAlertDialog")
        }
        binding.btnEdit.setOnClickListener {
            val newDialog = myEditNameDialogFrag()
            newDialog.show(requireActivity().supportFragmentManager, "myEditDialog")
        }
        binding.inlineButton.setOnClickListener {
            showInputDialog(
                "Inline Fragment"
            )
        }
        binding.mutliInputBtn.setOnClickListener {
            val newDialog = MultiInputDialogFragment.newInstance("Jim", null)
            newDialog.show(requireActivity().supportFragmentManager, "myMultiInputDialog")
        }
        return binding.root
    }

    /**
     * simple method to display data to the logger textview.
     */
    fun displaylog(item: String) {
        Log.v(TAG, item)
        //maybe called, before the fragment is created, so check if binding is initialized.
        if (!::binding.isInitialized) {
            Log.e(TAG, "binding is not initialized, cannot append log")
            return
        }
        binding.loggerCustom.append(item + "\n")
    }

    /**
     * We are going to add data
     * setup a dialog fragment to ask the user for the new item data or category.
     */
    fun showInputDialog(title: String?) {
        val inflater = layoutInflater
        val binding = LayoutCustomDialogBinding.inflate(inflater)

        val builder = AlertDialog.Builder(
            ContextThemeWrapper(
                activity, R.style.AppTheme_Dialog
            )
        )
        builder.setView(binding.root).setTitle(title)
        builder.setPositiveButton(
            "Add"
        ) { dialog, id ->
            mViewModel.setItem1("data is " + binding.itemAdded.text.toString())
            //Toast.makeText(getBaseContext(), userinput.getText().toString(), Toast.LENGTH_LONG).show();
        }
            .setNegativeButton(
                "Cancel"
            ) { dialog, id ->
                mViewModel.setYesNo(false)
                dialog.cancel()
            }
        //you can create the dialog or just use the now method in the builder.
        //AlertDialog dialog = builder.create();
        //dialog.show();
        builder.show()
    }
}
