package com.example.usuario.manageproductsfragments.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.usuario.manageproductsfragments.R;
import com.example.usuario.manageproductsfragments.interfaces.IValidateAccount;
import com.example.usuario.manageproductsfragments.interfaces.IValidateAccount.Presenter;
import com.example.usuario.manageproductsfragments.modelo.Error;
import com.example.usuario.manageproductsfragments.utils.ErrorMapsUtils;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenterImpl implements Presenter {
    private IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenterImpl(IValidateAccount.View view) {
        this.view = view;
        context = (Context)view;
    }

    public void validateCredentialsLogin(String user, String password) {
        validateUser = validateCredentialsUser(user);
        validatePassword = validateCredentialsPassword(password);

        if (validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                //Se puede utilizar la llamada al método startActivity con un Intent como
                //parámetro y no tener que implementar el método startActivity en la vista
                //porque llama al método super.startActivity dentro de la Activity.
                view.startActivity();
            } else {
                String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.til_password);
            }
        } else {
            String nameResource = ErrorMapsUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.til_user);
        }
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
}
