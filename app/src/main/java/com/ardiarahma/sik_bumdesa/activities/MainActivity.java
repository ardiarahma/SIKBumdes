package com.ardiarahma.sik_bumdesa.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AccountFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.AnggaranActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.BalanceFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.HomeFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.NeracaAwalActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.RABFragment;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.UserActivity;
import com.ardiarahma.sik_bumdesa.activities.navigation_drawer.UserFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("SIK Bumdes");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        displaySelectedScreen(R.id.nav_home);
        



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


//    public void displaySelectedScreen(int id){
//        Fragment fragment = null;
//        switch (id){
//            case R.id.nav_home:
//                fragment = new HomeFragment();
//                break;
//            case R.id.nav_akun:
//                fragment = new AccountFragment();
//                break;
//            case R.id.nav_neraca:
//                fragment = new BalanceFragment();
//                break;
//            case R.id.nav_rab:
//                fragment = new RABFragment();
//                break;
//            case R.id.nav_settings:
//                fragment = new UserFragment();
//                break;
//            case R.id.logout:
//                logoutConfirmation();
//                break;
//        }
//        if (fragment != null){
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.screen_area, fragment);
//            ft.commit();
//        }
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_akun:
                Intent intent1 = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_neraca:
                Intent intent2 = new Intent(MainActivity.this, NeracaAwalActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_rab:
                Intent intent3 = new Intent(MainActivity.this, AnggaranActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_settings:
                Intent intent4 = new Intent(MainActivity.this, UserActivity.class);
                break;
            case R.id.logout:
                logoutConfirmation();
                break;
        }
//        displaySelectedScreen(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logoutConfirmation(){
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);

    }
}
