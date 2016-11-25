package com.example.usuario.manageproductsrecycler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.usuario.manageproductsrecycler.interfaces.IProduct;
import com.example.usuario.manageproductsrecycler.modelo.Product;

/**
 * Created by usuario on 24/11/16.
 */

public class ConfirmDialog extends DialogFragment {
    private OnDeleteProductListener onDeleteProductListener;
    private Product product;

    public interface  OnDeleteProductListener {
        void deleteObject(Object object);
    }
    /*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        product = (Product) getArguments().getParcelable(IProduct.PRODUCT_KEY);
        return onCreateConfirmDialog();
    }

    public Dialog onCreateConfirmDialog() {
        final Product p = this.product;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    }
    */
}
