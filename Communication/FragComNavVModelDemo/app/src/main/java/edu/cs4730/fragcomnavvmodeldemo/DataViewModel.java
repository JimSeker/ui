package edu.cs4730.fragcomnavvmodeldemo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends AndroidViewModel {
    int num_one = 0;
    int num_two = 0;
    private MutableLiveData<String> item;

    public DataViewModel(@NonNull Application application) {
        super(application);
        item = new MutableLiveData<String>();
        item.setValue("default value");
    }

    public LiveData<String> getData() {
        return item;
    }

    String getItem() {
        return item.getValue();
    }

    void setItem(String n) {
        Log.d("VM", "data is " + n);
        item.setValue(n);
    }


}
