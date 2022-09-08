package edu.cs4730.fragcomnavlivedemo_kt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class DataViewModel : ViewModel() {
    private val num_one = MutableLiveData<Int?>()
    private val num_two = MutableLiveData<Int?>()
    private val item = MutableLiveData<String?>()

    val itemLD: LiveData<String?>
        get() {
            if (item.value == null) item.value = "default"
            return item
        }

    fun getItem(): String? {
        return item.value
    }

    fun setItem(n: String) {
        Log.d("VM", "data is $n")
        item.value = n
    }

    fun getoneLD(): LiveData<Int?> {
        if (num_one.value == null) num_one.value = 0
        return num_one
    }

    var one: Int
        get() = if (num_one.value != null) {
            num_one.value!!
        } else {
            0
        }
        set(value) {
            Log.d("ViewModel", "one is $value")
            num_one.value = value
        }

    fun incr_One() {
        num_one.value = num_one.value!! + 1
    }

    fun gettwoLD(): LiveData<Int?> {
        if (num_two.value == null) num_two.value = 0
        return num_two
    }

    var two: Int
        get() = if (num_two.value != null) {
            num_two.value!!
        } else {
            0
        }
        set(value) {
            Log.d("ViewModel", "two is $value")
            num_two.value = value
        }

    fun incr_Two() {
        //   if (num_two.getValue() != null)
        num_two.value = num_two.value!! + 1
    }
}