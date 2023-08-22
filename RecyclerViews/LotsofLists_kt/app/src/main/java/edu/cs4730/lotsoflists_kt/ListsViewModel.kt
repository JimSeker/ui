package edu.cs4730.lotsoflists_kt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ListsViewModel(application: Application) : AndroidViewModel(application) {
    private val myList = MutableLiveData<List<String>>()
    private val myCat = MutableLiveData<List<String>>()
    var length = 0
    var currentlist = 0
    var Categories: ArrayList<String>
    var listsoflist: MutableList<ArrayList<String>>

    init {
        Categories = ArrayList(mutableListOf("Phones"))
        listsoflist = ArrayList()
        listsoflist.add(
            ArrayList(
                mutableListOf(
                    "Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                    "Linux", "OS/2"
                )
            )
        )
        length = 1
        currentlist = 0
        //setup the live data initial.
        myList.value = listsoflist[currentlist]
        myCat.value = Categories
    }

    val listLD: LiveData<List<String>>
        get() = myList
    val catLD: LiveData<List<String>>
        get() = myCat

    fun setlist(i: Int) {
        currentlist = i
        myList.value = listsoflist[currentlist]
    }

    fun getlist(): Int {
        return currentlist
    }

    fun getsize(): Int {
        return listsoflist[currentlist].size
    }

    operator fun get(position: Int): String {
        return if (position < listsoflist[currentlist].size) {
            listsoflist[currentlist][position]
        } else {
            "Error!"
        }
    }

    fun addItem(item: String) {
        listsoflist[currentlist].add(item)
        myList.value = listsoflist[currentlist]
    }

    fun removeItem(item: Int) {
        listsoflist[currentlist].removeAt(item)
        myList.value = listsoflist[currentlist]
    }

    fun addCat(item: String) {
        Categories.add(item)
        //now create the list.
        length++
        listsoflist.add(ArrayList())
        myCat.value = Categories
    }

    fun getcatsize(): Int {
        return Categories.size
    }

    fun getcat(position: Int): String {
        return if (position < Categories.size) Categories[position] else "Cat error"
    }

    val allCat: List<String>
        get() = Categories
}