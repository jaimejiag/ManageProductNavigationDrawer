package com.example.usuario.manageproductsdb.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.usuario.manageproductsdb.R;
import com.example.usuario.manageproductsdb.interfaces.IValidateAccount;
import com.example.usuario.manageproductsdb.modelo.Error;
import com.example.usuario.manageproductsdb.preferences.AccountPreferencesImpl;
import com.example.usuario.manageproductsdb.utils.ErrorMapsUtils;

/**
 * Created by usuario on 16/11/16.
 */

public class SignUpPresenterImpl implements com.example.usuario.manageproductsdb.interfaces.SignUpPresenter.PresenterUser, com.example.usuario.manageproductsdb.interfaces.SignUpPresenter.Presenter {
    private int validateUser;
    private int validatePassword;
    private int validateEmail;
    private IValidateAccount.View view;
    private Context context;

    public SignUpPresenterImpl(IValidateAccount.View view) {
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
        AccountPreferencesImpl accountPreferencesImpl = (AccountPreferencesImpl) AccountPreferencesImpl.getInstance(context);
        accountPreferencesImpl.putUser(user);
        accountPreferencesImpl.putPassword(password);
        accountPreferencesImpl.putEmail(email);
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
