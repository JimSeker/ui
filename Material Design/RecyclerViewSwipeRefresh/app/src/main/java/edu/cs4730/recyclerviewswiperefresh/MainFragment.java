package edu.cs4730.recyclerviewswiperefresh;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple example of how to use the refreshlayout with a recyclerview.  most of the recyclerview and adapter
 * are from another recyclerview demo.
 *
 * the android.support.v4.widget.SwipeRefreshLayout is wrapped around the recyclerview in the layout.
 * In the code below we then set a listener when the refresh ("swipe down from top") is triggered.
 *   I see any thing for swiping up from the bottom of the list...
 *
 * some information was taken from this page
 * https://www.bignerdranch.com/blog/implementing-swipe-to-refresh/
 *
 *
 *
 */
public class MainFragment extends Fragment {
    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String[] values;

    public MainFragment() {
        //we need a really big list, for this example.  But only a few will be shown at any time.
        values = new String[]{
                "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
                "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
                "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
                "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
                "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
                "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
                "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde",
                "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
                "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
                "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
                "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
                "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
                "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
                "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar",
                "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
                "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary",
                "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
                "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
                "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
                "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
                "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
                "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
                "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas",
                "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
                "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
                "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena",
                "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
                "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
                "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
                "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea",
                "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden",
                "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
                "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
                "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda",
                "Ukraine", "United Arab Emirates", "United Kingdom",
                "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
                "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara",
                "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_main, container, false);

        //setup the RecyclerView
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //setup the adapter, which is myAdapter, see the code.
        mAdapter = new myAdapter(values, R.layout.my_row, getActivity());
        //add the adapter to the recyclerview
        mRecyclerView.setAdapter(mAdapter);

        //SwipeRefreshlayout setup.
        mSwipeRefreshLayout = (SwipeRefreshLayout) myView.findViewById(R.id.activity_main_swipe_refresh_layout);
        //setup some colors for the refresh circle.
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        //now setup the swiperefrestlayout listener where the main work is done.
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //where we call the refresher parts.  normally some kind of networking async task or web service.
                // For demo purpose, the code in the refreshslower method so it will take a couple of seconds
                //otherise, the task or service would just be called here.

                refreshslower();  //this will be slower, for the demo.
            }
        });

        //setup left/right swipes on the cardviews
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //likely allows to for animations?  or moving items in the view I think.
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //called when it has been animated off the screen.  So item is no longer showing.
                //use ItemtouchHelper.X to find the correct one.
                if (direction == ItemTouchHelper.RIGHT) {
                    //Toast.makeText(getBaseContext(),"Right?", Toast.LENGTH_SHORT).show();
                    int item =  viewHolder.getAdapterPosition(); //think this is where in the array it is.
                    mAdapter.removeitem(item);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);



        return myView;
    }

    /*
    * so this is just for demo purposed and will wait a specified time the last numbere there
    * listed in milliseconds.  so 1.5 seconds is 1500.  make it longer to see more colors in
    * the refreshing circle.
    */
    void refreshslower() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //update the listview with new values.
                mAdapter.randomlist();  //normally something better then a random update.
                mAdapter.notifyDataSetChanged();  //cause the recyclerview to update.
                mSwipeRefreshLayout.setRefreshing(false);  //turn of the refresh.
            }
        }, 2500);
    }
}
