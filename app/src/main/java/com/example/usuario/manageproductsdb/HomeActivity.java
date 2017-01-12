package com.example.usuario.manageproductsdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.usuario.manageproductsdb.fragments.ListProductFragment;
import com.example.usuario.manageproductsdb.fragments.ManageProductFragment;
import com.example.usuario.manageproductsdb.fragments.MultiListProductFragment;

/**
 * Created by usuario on 1/12/16.
 */

public class HomeActivity extends AppCompatActivity implements ManageProductFragment.ManageProductListener, MultiListProductFragment.MultiListProductListener {
    //Cambiar entre listProductFragment y multiListProductFragment para comprobar los distintos fragments.
    private ListProductFragment listProductFragment;
    private MultiListProductFragment multiListProductFragment;

    private ManageProductFragment manageProductFragment;
    private android.support.v7.widget.Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private boolean quitApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_nav);

        quitApp = false;

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigationdrawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDrawerContent();
        mActionBarDrawerToggle = setUpDrawerToggle();
        mDrawerLayout .addDrawerListener(mActionBarDrawerToggle);

        if (savedInstanceState == null) {
            listProductFragment = new ListProductFragment();
            multiListProductFragment = new MultiListProductFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.framehome, multiListProductFragment).commit();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mActionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.acction_settings_general:
                intent = new Intent(this, GeneralSettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.acction_settings_account:
                intent = new Intent(this, AccountSettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.action_home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que controla la opción seleccionada dentro del Navigation View
     */
    public void setupDrawerContent() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.action_products:
                        //onListProductListener();
                        break;

                    case R.id.action_pharmacy:
                        //onListPharmacyListener();
                        break;

                    default:
                        menuItem.setChecked(false);
                        break;
                }

                setTitle(menuItem.getTitle());
                mDrawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    public ActionBarDrawerToggle setUpDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProductFragment = new ManageProductFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framehome, manageProductFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void showListProduct(Bundle bundle) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framehome, multiListProductFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mToolbar.isShown())
            mToolbar.hideOverflowMenu();
        else if (quitApp) {
            finish();
        } else {
            Toast.makeText(this, "Pulsa de nuevo para salir.",
                    Toast.LENGTH_SHORT).show();
            quitApp = true;
        }
    }
}
