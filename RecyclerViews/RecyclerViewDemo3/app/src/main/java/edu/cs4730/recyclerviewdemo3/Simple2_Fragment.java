package edu.cs4730.recyclerviewdemo3;

/*
 * A second "simple" recyclerview with a more complex layout.
 *
 * again, everything associated with this example is prefixed with simple2_
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Simple2_Fragment extends Fragment {
    String TAG = "Simple2_rv";
    Context myContext;
	RecyclerView mRecyclerView;
	Simple2_myAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.simple1_fragment, container, false);
        //  NOTE, there is no xml layout for this activity!
        
		String[] values = new String[] {
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
		//setup the RecyclerView
		mRecyclerView = (RecyclerView) myView.findViewById(R.id.list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(myContext));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		//setup the adapter, which is myAdapter, see the code.
		mAdapter = new Simple2_myAdapter(values, R.layout.simple2_rowlayout, myContext);
		//add the adapter to the recyclerview
		mRecyclerView.setAdapter(mAdapter);
		return myView;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		myContext = context;
		Log.d(TAG, "onAttach");
	}


}
