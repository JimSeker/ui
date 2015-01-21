package edu.cs4730.ActionBarCompat;
/*
 * http://blog.stylingandroid.com/archives/2087
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{
	private MenuItem mSpinnerItem = null;
	private MenuItem mSearchItem = null;

	private EditText mSearch = null;
	private Button mDelete = null;

	private class MyTabListener implements ActionBar.TabListener
	{
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mFragName;

		public MyTabListener( Activity activity, String fragName )
		{
			mActivity = activity;
			mFragName = fragName;
		}

		@Override
		public void onTabReselected( Tab tab, FragmentTransaction ft )
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected( Tab tab, FragmentTransaction ft )
		{
			mFragment = Fragment.instantiate( mActivity, mFragName );
			ft.replace( android.R.id.content, mFragment );
		}

		@Override
		public void onTabUnselected( Tab tab, FragmentTransaction ft )
		{
			ft.remove( mFragment );
			mFragment = null;
		}
	}

	private ActionMode.Callback mCallback = new ActionMode.Callback()
	{

		@Override
		public boolean onPrepareActionMode( ActionMode mode, Menu menu )
		{
			return false;
		}

		@Override
		public void onDestroyActionMode( ActionMode mode )
		{
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onCreateActionMode( ActionMode mode, Menu menu )
		{
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate( R.menu.actionmode, menu );
			MenuItem item = menu.findItem( R.id.action_text );
            View v = MenuItemCompat.getActionView(item);
			if( v instanceof TextView )
			{
				((TextView)v).setText( R.string.actionmode_title );
			}
			return true;
		}

		@Override
		public boolean onActionItemClicked( ActionMode mode, MenuItem item )
		{
			boolean ret = false;
			if(item.getItemId() == R.id.actionmode_cancel) {
				mode.finish();
				ret = true;
			}
			return ret;
		}
	};

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		int mode = ActionBar.NAVIGATION_MODE_TABS;

		if (savedInstanceState != null)
		{
			mode = savedInstanceState.getInt( "mode",
					ActionBar.NAVIGATION_MODE_TABS );
		}
		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled( false );
		if (mode == ActionBar.NAVIGATION_MODE_TABS)
		{
			setTabNavigation( ab );
		} else
		{
			setListNavigation( ab );
		}
	}

	@Override
	protected void onSaveInstanceState( Bundle outState )
	{
		outState.putInt( "mode", getSupportActionBar().getNavigationMode() );
		super.onSaveInstanceState( outState );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.main, menu );
		mSpinnerItem = menu.findItem( R.id.menu_spinner );
		setupSpinner( mSpinnerItem );
		mSearchItem = menu.findItem( R.id.menu_search );
		mSearchItem
				.setVisible( getSupportActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS );
        View actionView = MenuItemCompat.getActionView(mSearchItem);
		mSearch = (EditText) actionView.findViewById(
				R.id.search );
		mDelete = (Button) actionView.findViewById(
				R.id.delete );
		mDelete.setVisibility( mSearch.getText().length() > 0 ? View.VISIBLE
				: View.GONE );
		mDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mSearch != null)
				{
					mSearch.setText( "" );
				}				
			}
		});
		mSearch.addTextChangedListener( new TextWatcher()
		{
			@Override
			public void onTextChanged( CharSequence s, int start, int before,
					int count )
			{
			}

			@Override
			public void beforeTextChanged( CharSequence s, int start,
					int count, int after )
			{
			}

			@Override
			public void afterTextChanged( Editable s )
			{
				mDelete.setVisibility( s.length() > 0 ? View.VISIBLE
						: View.GONE );
			}
		} );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		boolean ret;
		if (item.getItemId() == R.id.menu_settings)
		{
			// Handle Settings
			ret = true;
		} else if (item.getItemId() == R.id.menu_toggle)
		{
			ActionBar ab = getSupportActionBar();
			if (ab.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS)
			{
				setListNavigation( ab );
				mSearchItem.setVisible( false );
			} else
			{
				setTabNavigation( ab );
				mSearchItem.setVisible( true );
			}
			ret = true;
		} else if( item.getItemId() == R.id.menu_actionmode)
		{
			startSupportActionMode(mCallback);
			ret = true;
		} else
		{
			ret = super.onOptionsItemSelected( item );
		}
		return ret;
	}

	private void setTabNavigation( ActionBar actionBar )
	{
		actionBar.removeAllTabs();
		actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );
		actionBar.setTitle( R.string.app_name );

		Tab tab = actionBar
				.newTab()
				.setText( R.string.frag1 )
				.setTabListener(
						new MyTabListener( this, Fragment1.class.getName() ) );
		actionBar.addTab( tab );

		tab = actionBar
				.newTab()
				.setText( R.string.frag2 )
				.setTabListener(
						new MyTabListener( this, Fragment2.class.getName() ) );
		actionBar.addTab( tab );
		if (mSpinnerItem != null)
		{
			mSpinnerItem.setVisible( false );
		}
	}

	private void setListNavigation( ActionBar actionBar )
	{
		actionBar.setNavigationMode( ActionBar.NAVIGATION_MODE_LIST );

		final List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "title", getString( R.string.frag1 ) );
		map.put( "fragment",
				Fragment.instantiate( this, Fragment1.class.getName() ) );
		data.add( map );
		map = new HashMap<String, Object>();
		map.put( "title", getString( R.string.frag2 ) );
		map.put( "fragment",
				Fragment.instantiate( this, Fragment2.class.getName() ) );
		data.add( map );
		SimpleAdapter adapter = new SimpleAdapter( getSupportActionBar().getThemedContext(), data,
				R.layout.support_simple_spinner_dropdown_item,
				new String[] { "title" }, new int[] { android.R.id.text1 } );
		actionBar.setListNavigationCallbacks( adapter,
				new ActionBar.OnNavigationListener()
				{
					@Override
					public boolean onNavigationItemSelected( int itemPosition,
							long itemId )
					{
						Map<String, Object> map = data.get( itemPosition );
						Object o = map.get( "fragment" );
						if (o instanceof Fragment)
						{
							FragmentTransaction tx = getSupportFragmentManager()
									.beginTransaction();
							tx.replace( android.R.id.content, (Fragment) o );
							tx.commit();
						}
						return true;
					}
				} );
		if (mSpinnerItem != null)
		{
			setupSpinner( mSpinnerItem );
		}
	}

	private void setupSpinner( MenuItem item )
	{
		item.setVisible( getSupportActionBar().getNavigationMode() == ActionBar.NAVIGATION_MODE_LIST );
		View view = MenuItemCompat.getActionView(item);
		if (view instanceof Spinner)
        {
            Spinner spinner = (Spinner) view;
            spinner.setAdapter( ArrayAdapter.createFromResource( getSupportActionBar().getThemedContext(),
                    R.array.spinner_data,
                    R.layout.support_simple_spinner_dropdown_item ) );
        }
	}
}
