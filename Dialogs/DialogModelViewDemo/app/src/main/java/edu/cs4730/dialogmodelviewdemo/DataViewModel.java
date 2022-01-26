package edu.cs4730.dialogmodelviewdemo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    private MutableLiveData<String> item1;
    private MutableLiveData<String> item2;
    private MutableLiveData<Boolean> yesno;

    public DataViewModel() {
        item1 = new MutableLiveData<String>();
        item2 = new MutableLiveData<String>();
        yesno = new MutableLiveData<Boolean>();
        item1.setValue("Nothing");
        item2.setValue("Nothing");
        yesno.setValue(false);
    }
    //first item
    LiveData<String> getItem1LD() {
        item1.setValue("Nothing");
        return item1;
    }

    String getItem1() {
        return item1.getValue();
    }

    void setItem1(String n) {
        Log.d("VM", "item1 is " + n);
        item1.setValue(n);
    }

    //second item
    LiveData<String> getItem2LD() {
        item2.setValue("Nothing");
        return item2;
    }

    String getItem2() {
        return item2.getValue();
    }

    void setItem2(String n) {
        Log.d("VM", "item2 is " + n);
        item2.setValue(n);
    }

    //and yesno boolean
    LiveData<Boolean> getYesNoLD() {
        yesno.setValue(false);
        return yesno;
    }

    Boolean getYesNo() {
        return yesno.getValue();
    }

    void setYesNo(Boolean n) {
        Log.d("VM", "YesNo is " + n);
        yesno.setValue(n);
    }

}
