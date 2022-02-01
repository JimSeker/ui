Android RecyclerView Examples
==============

<b>RecyclerViewDemo</b> (java) is a very simple example of how to use a RecyclerView and CardView. 

<b>RecyclerViewDemo_kt</b> (kotlin) is a very simple example of how to use a RecyclerView and CardView.   

<b>RecyclerViewDemo2</b> (java) is more complex version using RecyclerView and CardView. This example uses the phonelist from the listview example as the data set.

<b>RecyclerViewDemo2_kt</b> (kotlin) is more complex version using RecyclerView and CardView. This example uses the phonelist from the listview example as the data set.

<b>RecyclerViewDemo3</b> is a rewrite of the listdemo from the listview using RecyclerView and CardView. It has everything in fragments and uses a viewpager to switch between the examples.

<b>RecyclerViewSwipeRefresh</b> (java) based on RecyclerViewDemo, but adds a swipeRefreashLayout so the user can trigger a refresh of data.

<b>RecyclerViewSwipeRefresh_kt</b> (kotlin) based on RecyclerViewDemo, but adds a swipeRefreashLayout so the user can trigger a refresh of data.

<b>LotsofLists</b> (java) is a complex demo using a Navigation bar and RecyclerView to display several different lists, plus you can add more items the lists and add more lists to the navigation as well.  The data is all stored in a ViewModel and observers are used the update the lists.

<b>LotsofLists_kt</b> (kotlin) is a complex demo using a Navigation bar and RecyclerView to display several different lists, plus you can add more items the lists and add more lists to the navigation as well.  The data is all stored in a ViewModel and observers are used the update the lists.

<b>CallBacksDemo</b> (java) uses a RecyclerView and shows how to setup a listeners and callbacks to pass data from the recyclerview adapter to the MainActivity (via a fragment).

<b>CallBacksDemo</b> (kotlin) uses a RecyclerView and shows how to setup a listeners and callbacks to pass data from the recyclerview adapter to the MainActivity (via a fragment). Kotlin actually seems to make the callbacks even more of a mess.  I recommend a modelview, instead of callbacks.

<b>CallBacksItemViewDemo</b> (java) uses a RecyclerView and sets each itemview to have a listener, so a user can click on the item in the recyclerview.  This uses a set of callbacks to get the data back to main activity (via a fragment) via the adapter.  

<b>CallBacksItemViewDemo_kt</b> (kotlin) uses a RecyclerView and sets each itemview to have a listener, so a user can click on the item in the recyclerview.  This uses a set of callbacks to get the data back to main activity (via a fragment) via the adapter.  again, modelview is easier.

<b>ModelViewRecyeclerViewDemo</b> (java) uses a recyclerview and instead of all the callbacks like in CallBackDemo, it uses a ModelView with LiveData to trigger observers in the Fragment and Activity to pass data back from the recyclerview adapter.

<b>ModelViewRecyeclerViewDemo_kt</b> (kotlin) uses a recyclerview and instead of all the callbacks like in CallBackDemo, it uses a ModelView with LiveData to trigger observers in the Fragment and Activity to pass data back from the recyclerview adapter.

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course and cosc 4735 Advance Mobile Programing course.  All examples are for Android.
