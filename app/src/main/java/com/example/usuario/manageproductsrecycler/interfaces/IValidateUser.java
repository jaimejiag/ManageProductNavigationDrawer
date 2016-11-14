package com.example.usuario.manageproductsrecycler.interfaces;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.usuario.manageproductsrecycler.modelo.Error;
/**
 * Created by usuario on 6/10/16.
 */
public interface IValidateUser extends IValidateAccount{
    int EMAIL_INVALID = 14;

    interface Presenter {
        static  int validateCredential(String email){
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return Error.EMAIL_INVALID;
            else
                return Error.OK;
        }
    }
}
