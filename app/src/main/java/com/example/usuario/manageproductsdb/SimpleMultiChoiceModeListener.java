package com.example.usuario.manageproductsdb;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import com.example.usuario.manageproductsdb.presenter.MultiChoicePresenter;

import java.util.ArrayList;

/**
 * Created by usuario on 16/12/16.
 */

public class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener{
    private Context context;
    private MultiChoicePresenter presenter;
    private int statusBarColor;
    private int cont;
    private ArrayList<View> listView;


    public SimpleMultiChoiceModeListener(Context context, ArrayList<View> listView, MultiChoicePresenter presenter) {
        this.context = context;
        this.listView = listView;
        cont = 0;
    }

    /**
     * Se va a ejecutar cuando se selecciona o se deselecciona el elemento de la lista.
     * @param mode
     * @param position
     * @param id
     * @param checked Devuelve true si se ha seleccionado o false si no lo ha sido.
     */
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            cont++;
            presenter.setNewSelection(position, checked);
        } else {
            cont--;
            presenter.removeSelection(position);
        }

        mode.setTitle(cont);
    }

    /**
     * Es donde se está creando el menú de acción.
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);

        for (View v: listView) {
            v.setVisibility(View.VISIBLE);
        }
        return true;
    }

    /**
     * Se lanza cuando se actualiza el menú.
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusBarColor =((AppCompatActivity)context).getWindow().getStatusBarColor();
            ((AppCompatActivity)context).getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.background_action_mode));
        }

        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_product:
                presenter.deleteSelectedProduct();
                break;
        }

        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity)context).getWindow().setStatusBarColor(statusBarColor);
        }

        presenter.clearSelection();

        for (View v: listView) {
            v.setVisibility(View.VISIBLE);
        }

        cont = 0;
    }
}
