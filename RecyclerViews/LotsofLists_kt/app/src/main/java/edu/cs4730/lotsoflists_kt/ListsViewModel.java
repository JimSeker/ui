package edu.cs4730.lotsoflists_kt;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ListsViewModel extends AndroidViewModel {

    private MutableLiveData<List<String>> myList = new MutableLiveData<List<String>>();
    private MutableLiveData<List<String>> myCat = new MutableLiveData<List<String>>();

    int length = 0;
    int currentlist = 0;
    ArrayList<String> Categories;
    List<ArrayList<String>> listsoflist;

    public ListsViewModel(@NonNull Application application) {
        super(application);

        Categories = new ArrayList<String>(Arrays.asList("Phones"));
        listsoflist = new ArrayList<ArrayList<String>>();

        listsoflist.add(new ArrayList<String>(Arrays.asList("Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2")));
        length = 1;
        currentlist = 0;
        //setup the live data initial.
        myList.setValue(listsoflist.get(currentlist));
        myCat.setValue(Categories);
    }

    LiveData<List<String>> getListLD() {
        return myList;
    }

    LiveData<List<String>> getCatLD() {
        return myCat;
    }

    public void setlist(int i) {
        currentlist = i;
        myList.setValue(listsoflist.get(currentlist));
    }

    public int getlist() {
        return currentlist;
    }
    public int getsize() {
        return listsoflist.get(currentlist).size();
    }

    public String get(int position) {
        if (position < listsoflist.get(currentlist).size()) {
            return listsoflist.get(currentlist).get(position);
        } else {
            return "Error!";
        }
    }

    public void addItem(String item) {
        listsoflist.get(currentlist).add(item);
        myList.setValue(listsoflist.get(currentlist));
    }

    public void removeItem(int item) {
        listsoflist.get(currentlist).remove(item);
        myList.setValue(listsoflist.get(currentlist));
    }

    public void addCat(String item) {
        Categories.add(item);
        //now create the list.
        length++;
        listsoflist.add(new ArrayList<String>());
        myCat.setValue(Categories);
    }


    public int getcatsize() {
        return Categories.size();
    }

    public String getcat(int position) {
        if (position < Categories.size())
            return Categories.get(position);
        else
            return "Cat error";
    }

    public List<String> getAllCat() {
        return Categories;
    }


}
