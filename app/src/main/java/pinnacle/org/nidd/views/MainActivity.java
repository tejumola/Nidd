package pinnacle.org.nidd.views;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import pinnacle.org.nidd.R;
import pinnacle.org.nidd.common.BaseActivity;
import pinnacle.org.nidd.fragment.ChatFragment;

/**
 * Created by DOTECH on 28/04/2016.
 */
public class MainActivity extends BaseActivity{

    private static final int REQUEST_FINE_LOCATION=0;//location based permission
    private boolean locationPermissionGranted;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private NavigationView.OnNavigationItemSelectedListener mNavigationItemSelectedListener= new  NavigationView.OnNavigationItemSelectedListener( ) {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            mDrawerLayout.closeDrawers();
            int id=item.getItemId ();

            if (id == R.id.nav_chats) {
                addChatFragment();
            }
//do for other tabs here
            if(id==R.id.nav_logout){
                goToNextActivity ( SignInActivity.class,null ,null);
            }

            checkItem ( item );
            return true;
        }

        public void checkItem(MenuItem item) {
            item.setEnabled ( true );
            item.setCheckable ( true );
            item.setChecked ( true );

        }
    };
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //set the location based permission
        if(!locationPermissionGranted)
            loadPermissions( Manifest.permission.ACCESS_FINE_LOCATION,REQUEST_FINE_LOCATION);
        super.onCreate ( savedInstanceState );


        initDrawer ( );
        mNavigationView.setCheckedItem(0);
        addChatFragment ( );

    }

    private void addChatFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace( R.id.containerView, ChatFragment.instantiate (this,ChatFragment.class.getCanonicalName ()) ).commit();
    }


    private void initDrawer() {
        mDrawerLayout= (DrawerLayout) findViewById ( R.id.drawer_layout );
        mNavigationView=(NavigationView) findViewById ( R.id.nav_view );
       // mNavigationView.setItemIconTintList(null);
        instantiateDrawerToggle ( );
        mDrawerLayout.addDrawerListener ( mDrawerToggle);
        new Handler().post (new Runnable ( ) {
            @Override
            public void run() {
                mDrawerToggle.syncState ();
            }
        } );
    }

    private void instantiateDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle ( getActivity(),
                mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close ){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed ( drawerView );
                mDrawerToggle.syncState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened ( drawerView );
                mDrawerToggle.syncState();
            }
        };
        mNavigationView.setNavigationItemSelectedListener ( mNavigationItemSelectedListener );
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate ( savedInstanceState );
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged ( newConfig );
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ( );
        mDrawerLayout.removeDrawerListener ( mDrawerToggle );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( mDrawerToggle.onOptionsItemSelected (item))
            return true;
        return super.onOptionsItemSelected ( item );

    }

    /**
     * create a factory for handling all tabs in the application
     * @return
     */
    @Override
    public Object getPresenter() {
        return null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    // granted
                    locationPermissionGranted=true;


            }

        }

    }
}
