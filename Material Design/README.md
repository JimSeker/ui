Android material Design Examples
==============

<b>SimpleDemo</b> is basic example of using Material design.  There is no real java code here, it's all in the values/color and styles.xml files (values and values-v21).  There are also two screenshots device-4.4.png and device-5.0.png to show how style looks.



<b>CirToolbarNavDrawerDemo</b> uses toolbarnavDrawerDemo and uses the suppor design floating action button.  Previous versions used adds the Floating Action button (using a library from https://github.com/makovkastar/FloatingActionButton) 
A useful guide to added libraries (see Studio 1.0 answer) http://stackoverflow.com/questions/16588064/how-do-i-add-a-library-project-to-the-android-studio A somewhat helpful visual guide to adding libraries: http://youtu.be/1MyBO9z7ojk 


<b>RecyclerViewDemo</b> is a very simple example of how to use a RecyclerView and CardView (via support.v7).   Note a RecyclerView is a more powerful listview, which was introduced into android 5 and the support.v7 library.

<b>RecyclerViewDemo2</b> is more complex version using RecyclerView and CardView (support.v7). This example uses the phonelist from the listview example as the data set.

<b>RecyclerViewDemo3</b> is a rewrite of the listdemo from the listview using RecyclerView and CardView (support.v7). It has everything in fragments and uses a viewpager to switch between the examples.

<b>RecyclerViewSwipeRefresh</b> based on RecyclerViewDemo, but adds a swipeRefreashLayout so the user can trigger a refresh of data.

<b>SupportDesignDemo</b> is demo of some of the support Design widgets.  Floating action Button, inputtextlayout, snack bar, and using the snackbar and floating action button together (hint the fab moves out of the way).

<b>snackbarDemo</b>  show the snackbar triggered from a fab.  It also show how the callbacks works, so you can determine how the
user dealt with the snackbar (ie swiped it away, timed out, etc ).

<b>SupportDesignDemo2</b> uses the support design tablayout with a viewpager.  with the new methods is support design the tablayout is just as easy to use as The stripetab with the viewpager, but you need 2 extra lines of code to setup it up.

<b>SupportDesignDemo3</b> show how to use a picture with the toolbar and how to collapse the picture.  Also a little bit with the support design pallete.

<b>SupportDesignDemo4</b> show how to use the larger toolbar and how to collapse it as well.

<b>SupportPaletteDemo</b> A simple example using a bitmap to get the palette and change the some textviews with it.  The example uses 4 different images that
change via a imagebutton.

<b>LotsofLists</b> is a complex demo using a Navigation bar and RecyclerView to display several different lists, plus you can add more items the lists and add more lists to the navigation as well.

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course.  All examples are for Android.
