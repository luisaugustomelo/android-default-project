package ni.com.debugon.notificationinfo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import ni.com.debugon.notificationinfo.ni.com.debugon.Fragments.ComplexFragment;
import ni.com.debugon.notificationinfo.ni.com.debugon.Fragments.FirstListFragment;
import ni.com.debugon.notificationinfo.ni.com.debugon.Fragments.SecondListFragment;
import ni.com.debugon.notificationinfo.ni.com.debugon.Fragments.SimpleFragment;
import ni.com.debugon.notificationinfo.ni.com.debugon.Service.AlarmReceiver;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private boolean viewIsAtHome;
    private boolean exit_app;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_you_need);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        long interval = 1*60*1000;
        setRepeatAlarm(interval);


        displayView(R.id.nav_view); //Fragmento default

    }

    public void setRepeatAlarm(long interval){
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),interval , pendingIntent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.nav_view); //display the News fragment
        } else {
            if(exit_app)
                moveTaskToBack(true);  //If view is in News fragment, exit application
            else
                exit_app = true;

            Snackbar snackbar = Snackbar
                    .make(drawer, getString(R.string.exit_messege), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.what_you_need, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        exit_app = false;

        switch (viewId) {
            case (R.id.nav_view) :
                fragment = new SimpleFragment();
                title = getString(R.string.nav_view);
                viewIsAtHome = true;
                break;

            case R.id.nav_search:
                fragment = new ComplexFragment();
                title = getString(R.string.nav_search);
                viewIsAtHome = false;
                break;

            case R.id.nav_recent_history:
                fragment = new FirstListFragment();
                title = getString(R.string.nav_recent_history);
                viewIsAtHome = false;
                break;

            case R.id.nav_seekers:
                fragment = new SecondListFragment();
                title = getString(R.string.nav_seekers);
                viewIsAtHome = false;
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_manage:
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_what_you_need, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
    }
}
