package edu.cs4730.listviewswiperefresh_kt

import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import java.util.*

/**
 * A simple example of how to use the refreshlayout with a listview.
 * androidx....SwipeRefreshLayout is wrapped around the listview in the layout.
 * In the code below we then set a listener when the refresh ("swipe down from top") is triggered.
 * I see any thing for swiping up from the bottom of the list...
 * <p>
 * some information was taken from this page
 * https://www.bignerdranch.com/blog/implementing-swipe-to-refresh/
 */


class MainActivity : AppCompatActivity() {

    var values = arrayOf(
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
    )


    lateinit var mListView: ListView
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    lateinit var mAdapter: ArrayAdapter<String>
    var mRandom = Random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSwipeRefreshLayout = findViewById(R.id.activity_main_swipe_refresh_layout)
        mListView = findViewById(R.id.ListView01)
        val fakelist = getRandomList()
        mAdapter = ArrayAdapter(this@MainActivity,
            android.R.layout.simple_list_item_1, fakelist
        )
        mListView.adapter = mAdapter

        //setup some colors for the refresh circle.
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue)
        //now setup the swiperefrestlayout listener where the main work is done.
        mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            //where we call the refresher parts.  normally some kind of networking async task or web service.
            //this is a bad way to do this, I'm just redoing the adapter, normally, the adapter would update
            //and then use the mAdapter.notifyDataSetChanged();

            /* normally something like this... but I want to slow it down for demo purposes, so it's commented out.
            val fakelist = getRandomList()
            mAdapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_list_item_1, fakelist
            )
            mListView.adapter = mAdapter
            //new turn off the refresh.
            mSwipeRefreshLayout.isRefreshing = false
            */
            refreshSlower() //this will be slower, for the demo.
        })
    }


    /**
     * so this is just for demo purposed and will wait a specified time the last numbere there
     * listed in milliseconds.  so 1.5 seconds is 1500.  make it longer to see more colors in
     * the refreshing circle.
     */
    fun refreshSlower() {
        Handler().postDelayed({
            //update the listview with new values.
            mAdapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_list_item_1, getRandomList()
            )
            mListView.adapter = mAdapter
            mSwipeRefreshLayout.isRefreshing = false //turn of the refresh.
        }, 1500)
    }

    //a very simple function to get me 20 random items in the list from a much longer list.
    fun getRandomList(): Array<String?> {
        val newNames = arrayOfNulls<String>(20)
        for (i in 0..19) {
            newNames[i] = values[mRandom.nextInt(values.size - 1)]
        }
        return newNames
    }
}