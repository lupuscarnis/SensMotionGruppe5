package com.example.nicolai.sensmotiongruppe5;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class nav_drawer extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener(){
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.nav_achi:
                toolbar.setTitle("Min side");
                fragment = new Min_Side_Activity();
                loadFragment(fragment);
                return true;
            case R.id.nav_set:
                toolbar.setTitle("Indstillinger");
                fragment = new Setting_Activity();
                loadFragment(fragment);
                return true;
            case R.id.nav_data:
                toolbar.setTitle("Min Data");
                fragment = new Min_Side_Activity();
                loadFragment(fragment);
                return true;
        }
        mDrawerLayout.closeDrawers();
        return false;
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toolbar.setTitle("Min side");
        loadFragment(new Min_Side_Activity());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}