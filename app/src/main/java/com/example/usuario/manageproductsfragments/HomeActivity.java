package com.example.usuario.manageproductsfragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.usuario.manageproductsfragments.fragments.ListProductFragment;
import com.example.usuario.manageproductsfragments.fragments.ManageProductFragment;
import com.example.usuario.manageproductsfragments.interfaces.IProduct;

/**
 * Created by usuario on 1/12/16.
 */

public class HomeActivity extends AppCompatActivity implements ManageProductFragment.ManageProductListener,
        ListProductFragment.ListProductListener {
    private ListProductFragment listProductFragment;
    private ManageProductFragment manageProductFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listProductFragment = new ListProductFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome, listProductFragment).commit();
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
        fragmentTransaction.replace(R.id.framehome, listProductFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
