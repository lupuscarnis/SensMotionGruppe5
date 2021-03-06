package com.example.nicolai.sensmotiongruppe5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.nicolai.sensmotiongruppe5.Activities.Login_Activity;
import com.example.nicolai.sensmotiongruppe5.Fragments.Achieve_Fragment;
import com.example.nicolai.sensmotiongruppe5.Fragments.GMaps_Fragment;
import com.example.nicolai.sensmotiongruppe5.Fragments.Min_Data_Fragment;
import com.example.nicolai.sensmotiongruppe5.Fragments.Min_Side_Fragment;
import com.example.nicolai.sensmotiongruppe5.Fragments.Score_Fragment;
import com.example.nicolai.sensmotiongruppe5.Interface.IChild_OnFragmentInteractionListener;
import com.example.nicolai.sensmotiongruppe5.Interface.IParent_OnFragmentInteractionListener;

public class nav_drawer extends AppCompatActivity implements IParent_OnFragmentInteractionListener, IChild_OnFragmentInteractionListener {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_achie:
                    toolbar.setTitle("Achievements");
                    fragment = new Achieve_Fragment();
                    loadFragment(fragment);
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.nav_set:
                    toolbar.setTitle("Highscore");
                    fragment = new Score_Fragment();
                    mDrawerLayout.closeDrawers();
                    loadFragment(fragment);
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.nav_data:
                    toolbar.setTitle("Min data");
                    fragment = new Min_Data_Fragment();
                    loadFragment(fragment);
                    mDrawerLayout.closeDrawers();
                    return true;
                case R.id.nav_side:
                    toolbar.setTitle("Min side");
                    fragment = new Min_Side_Fragment();
                    loadFragment(fragment);
                    mDrawerLayout.closeDrawers();

                    return true;
                case R.id.nav_map:
                    toolbar.setTitle("Kort");
                    fragment = new GMaps_Fragment();
                    loadFragment(fragment);
                    mDrawerLayout.closeDrawers();

                    return true;

                case R.id.nav_logud:
                    Intent intentUd = new Intent(nav_drawer.this, Login_Activity.class);
                    startActivity(intentUd);

            }
            mDrawerLayout.closeDrawers();
            return false;
        }
    };


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

        Intent intent = getIntent();
        String achi = intent.getStringExtra("achi");
        String achi2 = "achi";


        if (achi2.equals(achi)) {
            toolbar.setTitle("Achievements");
            loadFragment(new Achieve_Fragment());
        } else {


            toolbar.setTitle("Min side");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, new Min_Side_Fragment());
            transaction.commit();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        transaction.commit();
    }

    public void messageFromParentFragment(Uri uri) {
        Log.i("TAG", "received communication from parent fragment");
    }


    public void messageFromChildFragment(Uri uri) {
        Log.i("TAG", "received communication from child fragment");
    }


}
