package com.example.usuario.manageproductsrecycler.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.usuario.manageproductsrecycler.ProductsActivity;
import com.example.usuario.manageproductsrecycler.R;
import com.example.usuario.manageproductsrecycler.interfaces.IValidateAccount;
import com.example.usuario.manageproductsrecycler.interfaces.IValidateUser;
import com.example.usuario.manageproductsrecycler.modelo.Error;
import com.example.usuario.manageproductsrecycler.modelo.User;
import com.example.usuario.manageproductsrecycler.preferences.AccountPreferences;
import com.example.usuario.manageproductsrecycler.utils.ErrorMapsUtils;

/**
 * Created by usuario on 16/11/16.
 */

public class SignUpPresenter implements IValidateUser.PresenterUser, IValidateUser.Presenter {
    private int validateUser;
    private int validatePassword;
    private int validateEmail;
    private IValidateAccount.View view;
    private Context context;

    public SignUpPresenter (IValidateAccount.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    public void validateCredentials(String user, String password, String email) {
        validateUser = validateCredentialsUser(user);
        validatePassword = validateCredentialsPassword(password);
        validateEmail = validateCredentialEmail(email);

        if (validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                if (validateEmail == Error.OK) {
                    //Guardarmos en las preferencias.
                    savePreferences(user, password, email);
                    view.startActivity();
                } else {
                    String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validateEmail));
                    view.setMessageError(nameResource, R.id.til_userEmail);
                }
            } else {
                String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.til_userPassword);
            }
        } else {
            String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.til_userName);
        }
    }

    private void savePreferences(String user, String password, String email) {
        AccountPreferences accountPreferences = (AccountPreferences)AccountPreferences.getInstance(context);
        accountPreferences.putUser(user);
        accountPreferences.putPassword(password);
        accountPreferences.putEmail(email);
    }

    public int validateCredentialsUser(String user) {
        if (TextUtils.isEmpty(user)) {
            return Error.DATA_EMPTY;
        }

        return Error.OK;
    }

    public int validateCredentialsPassword(String password) {
        int result = Error.OK;

        if (TextUtils.isEmpty(password)) {
            result = Error.DATA_EMPTY;
        } else if (!password.matches(".*[0-9].*")) {
            result = Error.PASSWORD_DIGIT;
        } else if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
            result = Error.PASSWORD_CASE;
        } else if (password.length() < 8)
            result = Error.PASSWORD_LENGTH;

        return result;
    }

    public int validateCredentialEmail(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return Error.EMAIL_INVALID;
        else
            return Error.OK;
    }
}
