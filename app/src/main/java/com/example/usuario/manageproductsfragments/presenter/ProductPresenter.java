package com.example.usuario.manageproductsfragments.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.usuario.manageproductsfragments.aplications.ProductApplication;
import com.example.usuario.manageproductsfragments.R;
import com.example.usuario.manageproductsfragments.interfaces.LoginPresenter;
import com.example.usuario.manageproductsfragments.modelo.Product;

/**
 * Created by jaime on 23/10/16.
 */

public class ProductPresenter implements LoginPresenter.Presenter {
    private LoginPresenter.View view;

    public ProductPresenter (LoginPresenter.View view){
        this.view = view;
    }

    @Override
    public boolean validateCredentials(String name, String description, String dosage,
            String brand, String price, String stock) {

        Product product;
        boolean result = false;

        if (TextUtils.isEmpty(name))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_name);
        else if (TextUtils.isEmpty(description))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_description);
        else if (TextUtils.isEmpty(dosage))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_dosage);
        else if (TextUtils.isEmpty(brand))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_brand);
        else if (TextUtils.isEmpty(price))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_price);
        else  if (TextUtils.isEmpty(stock))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_stock);
        else {
            product = new Product(name, description, dosage, brand, Double.parseDouble(price),
                    Integer.parseInt(stock), R.drawable.pill);
            ((ProductApplication) ((Context) view).getApplicationContext()).saveProduct(product);
            result = true;
        }

        return result;
    }
}
