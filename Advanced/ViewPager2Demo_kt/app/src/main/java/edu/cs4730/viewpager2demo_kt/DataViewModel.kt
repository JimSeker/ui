package edu.cs4730.viewpager2demo_kt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

/**
 * this is class to hold data so even when the destroy view is called for the fragment
 * the data can survive.   The left and right fragment are creating their own instance of this object.
 */
class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val left: MutableLiveData<String> = MutableLiveData("left")
    private val right: MutableLiveData<String> = MutableLiveData("right")
    val dataLeft: LiveData<String>
        get() = left
    val dataRight: LiveData<String>
        get() = right
    val strLeft: String?
        get() = left.value
    val strRight: String?
        get() = right.value

    fun setStrLeft(item: String) {
        left.value = item
    }

    fun setStrRight(item: String) {
        right.value = item
    }

    fun appendStrRight(item: String) {
        val prev = right.value
        right.value = "$prev\n$item"
    }

    fun appendStrLeft(item: String) {
        val prev = left.value
        left.value = "$prev\n$item"
    }

}