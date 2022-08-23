package edu.cs4730.dialogviewmodeldemo_kt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private val item1: MutableLiveData<String> = MutableLiveData("Nothing")
    private val item2: MutableLiveData<String> = MutableLiveData("Nothing")
    private val yesno: MutableLiveData<Boolean> = MutableLiveData(false)

    //first item
    val getItem1LD: LiveData<String>
        get() {
            item1.value = "Nothing"
            return item1
        }

    fun getItem1(): String? {
        return item1.value
    }

    fun setItem1(n: String) {
        Log.d("VM", "item1 is $n")
        item1.value = n
    }

    //second item
    val getItem2LD: LiveData<String>
        get() {
            item2.value = "Nothing"
            return item2
        }

    fun getItem2(): String? {
        return item2.value
    }

    fun setItem2(n: String) {
        Log.d("VM", "item2 is $n")
        item2.value = n
    }

    //and yesno boolean
    val getYesNoLD: LiveData<Boolean>
        get() {
            yesno.value = false
            return yesno
        }

    val yesNo: Boolean?
        get() = yesno.value

    fun setYesNo(n: Boolean) {
        Log.d("VM", "YesNo is $n")
        yesno.value = n
    }

}
