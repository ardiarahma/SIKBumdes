package com.ardiarahma.sik_bumdesa.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.dashboard.BukuBesarActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.EkuitasActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.LabaRugiActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.NeracaActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.BalanceFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.HomeFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.RABFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.UserFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Dashboard");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_home);
        



    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void displaySelectedScreen(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_akun:
                fragment = new AccountFragment();
                break;
            case R.id.nav_neraca:
                fragment = new BalanceFragment();
                break;
            case R.id.nav_rab:
                fragment = new RABFragment();
                break;
            case R.id.nav_settings:
                fragment = new UserFragment();
                break;
            case R.id.logout:
                logoutConfirmation();
                break;
        }
        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.screen_area, fragment);
            ft.commit();
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    public void logoutConfirmation(){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);

    }
}
