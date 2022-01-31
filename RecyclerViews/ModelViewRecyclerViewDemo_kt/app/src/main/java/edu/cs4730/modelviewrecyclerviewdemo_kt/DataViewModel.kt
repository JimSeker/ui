package edu.cs4730.modelviewrecyclerviewdemo_kt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

/**
 * a simple class to store the data string name is in.
 *
 *
 * note, lint is stupid and says they class should be private.  it can't be or it dies..
 */
class DataViewModel : ViewModel() {
    private val item = MutableLiveData<String>()

    //note, no constructor seems to be allowed, or it dies.
    val itemLD: LiveData<String>
        get() {
            item.value = "default"
            return item
        }

    fun getItem(): String {
        return item.value.toString()
    }

    fun setItem(n: String) {
        Log.d("VM", "data is $n")
        item.value = n
    }
}