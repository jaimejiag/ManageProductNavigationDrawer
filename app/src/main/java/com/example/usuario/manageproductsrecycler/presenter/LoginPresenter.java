package com.example.usuario.manageproductsrecycler.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.usuario.manageproductsrecycler.ProductsActivity;
import com.example.usuario.manageproductsrecycler.R;
import com.example.usuario.manageproductsrecycler.interfaces.IValidateAccount;
import com.example.usuario.manageproductsrecycler.interfaces.IValidateAccount.Presenter;
import com.example.usuario.manageproductsrecycler.modelo.Error;
import com.example.usuario.manageproductsrecycler.utils.ErrorMapsUtils;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenter implements Presenter {
    private IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenter(IValidateAccount.View view) {
        this.view = view;
        context = (Context)view;
    }

    public void validateCredentialsLogin(String user, String password) {
        validateUser = Presenter.validateCredentialsUser(user);
        validatePassword = Presenter.validateCredentialsPassword(password);

        if (validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                Intent intent = new Intent((Context) view, ProductsActivity.class);
                view.startActivity(intent);
            } else {
                String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.til_password);
            }
        } else {
            String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.til_user);
        }
    }
}
