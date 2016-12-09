package com.example.usuario.manageproductsfragments.interfaces;


/**
 * Created by usuario on 6/10/16.
 */
public interface SignUpPresenter extends IValidateAccount {
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
