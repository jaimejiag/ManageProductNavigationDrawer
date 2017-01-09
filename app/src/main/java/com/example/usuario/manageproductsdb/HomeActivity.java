package com.example.usuario.manageproductsdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listProductFragment = new ListProductFragment();
        multiListProductFragment = new MultiListProductFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome, multiListProductFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
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
        }

        return super.onOptionsItemSelected(item);
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


}
