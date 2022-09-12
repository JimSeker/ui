package edu.cs4730.fragcomnavvmodeldemo_kt

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class DataViewModel(application: Application) : AndroidViewModel(application) {
    @JvmField
    var num_one = 0
    @JvmField
    var num_two = 0
    private val item: MutableLiveData<String> = MutableLiveData()
    val data: LiveData<String>
        get() = item

    fun getItem(): String? {
        return item.value
    }

    fun setItem(n: String) {
        Log.d("VM", "data is $n")
        item.value = n
    }

    init {
        item.value = "default value"
    }
}