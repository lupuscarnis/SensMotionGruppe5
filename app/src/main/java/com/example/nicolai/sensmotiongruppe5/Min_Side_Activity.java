package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nicolai.sensmotiongruppe5.BLL.DAO;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class Min_Side_Activity extends AppCompatActivity implements View.OnClickListener{

    ImageButton indstil;
     Button help;
    // DAO singleton instance object
    DAO userDAO = DAO.getInstance();
    // For storing the values from JSON
    List<List<String>> valuesArray;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(


            new NavigationView.OnNavigationItemSelectedListener() {

                @SuppressWarnings("StatementWithEmptyBody")
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
// Handle navigation view item clicks here.
                    int id = item.getItemId();
                    if (id == R.id.nav_achi) {
                        Intent intent1 = new Intent(Min_Side_Activity.this, Setting_Activity.class);
                        startActivity(intent1);
                    }
                    else if (id == R.id.nav_set) {
                        Intent intent2 = new Intent(Min_Side_Activity.this, Setting_Activity.class);
                        startActivity(intent2);
                    }
                    else if (id == R.id.nav_test1) {
                        Intent intent3 = new Intent(Min_Side_Activity.this, Setting_Activity.class);
                        startActivity(intent3);
                    }
                    else if (id == R.id.nav_test2) {
                        Intent intent4 = new Intent(Min_Side_Activity.this, Setting_Activity.class);
                        startActivity(intent4);
                    }

                    else if (id == R.id.nav_logud) {
                        Intent intent5 = new Intent(Min_Side_Activity.this, Login_Activity.class);
                        startActivity(intent5);

                    }
                    mDrawerLayout.closeDrawers();

                    // Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    return true;

                }

              });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }});


        new GetJSON().execute();
        help = findViewById(R.id.Min_side_help);
        help.setOnClickListener(this);


    }
            @Override
            public boolean onOptionsItemSelected(MenuItem item){
                switch (item.getItemId()) {
                    case android.R.id.home:
                        mDrawerLayout.openDrawer(GravityCompat.START);
                        return true;
                }
                return super.onOptionsItemSelected(item);

            }

    public void openSettingActivity(){
        Intent intent = new Intent(this, Setting_Activity.class);
        startActivity(intent);
    }


    /*
     * Download JSON as ASYNCTASK
     * Prompts to screen
     */

    private class GetJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Toast.makeText(Min_Side_Activity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

            //userDAO.getData();
            valuesArray = userDAO.getData();
            return null;

        }
        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            // Save JSON_STRING to phone
            SharedPreferences.Editor prefEditor = getDefaultSharedPreferences(Min_Side_Activity.this).edit();
            prefEditor.putString("JSON_STRING", valuesArray.toString()).apply();

            // Retrieve JSON_STRING from phone
            //String jsonString = PreferenceManager.getDefaultSharedPreferences(Min_Side_Activity.this).getString("JSON_STRING", "DefaultStringIfNULL");
            //Log.d("Debug SharedPreferences",""+jsonString);

            // Toast when done downloading/parsing JSON
            Toast.makeText(Min_Side_Activity.this,"Json Data example info "+valuesArray.get(0).get(1),Toast.LENGTH_LONG).show();
        }
    }
public void onClick(View v){
    Intent   i = new Intent(this, Min_Data_Activity.class);
    startActivity(i);

}




}
