package com.example.usuario.manageproductsrecycler.interfaces;

import android.content.Intent;
import android.text.TextUtils;
import com.example.usuario.manageproductsrecycler.modelo.Error;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateAccount {

    interface  View {
        void setMessageError(String error, int viewId);
        void startActivity();
    }

    interface Presenter {
        int validateCredentialsUser(String user);
        int validateCredentialsPassword(String password);

        /*static int validateCredentialsUser(String user) {
            if (TextUtils.isEmpty(user)) {
                return Error.DATA_EMPTY;
            }

            return Error.OK;
        }

        static int validateCredentialsPassword(String password) {
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
        }*/
    }
}
