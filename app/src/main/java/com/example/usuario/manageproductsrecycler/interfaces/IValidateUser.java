package com.example.usuario.manageproductsrecycler.interfaces;


import android.util.Patterns;

import com.example.usuario.manageproductsrecycler.modelo.Error;

/**
 * Created by usuario on 6/10/16.
 */
public interface IValidateUser extends IValidateAccount {
    int EMAIL_INVALID = 14;

    interface PresenterUser {
        int validateCredentialEmail(String email);

        /*static int validateCredentialEmail(String email) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return Error.EMAIL_INVALID;
            else
                return Error.OK;
        }*/
    }
}
