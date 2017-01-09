package com.example.usuario.manageproductsdb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.usuario.manageproductsdb.interfaces.ProductPresenter;
import com.example.usuario.manageproductsdb.modelo.Product;

/**
 * Created by usuario on 24/11/16.
 */

public class ConfirmDialog extends DialogFragment {
    private OnDeleteProductListener onDeleteProductListener;
    private Product product;
    private ProductPresenter mPresenter;


    public interface  OnDeleteProductListener {
        void deleteObject(Object object);

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        product = getArguments().getParcelable("product");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Desea eliminar el elemento " + product.getmName() + "?")
                .setTitle("Confirmación")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mPresenter.deleteProduct(product);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    public void setPresenter(ProductPresenter presenter){
        this.mPresenter = presenter;
    }
}
