package com.example.usuario.loginrelative;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view) {
        this.view = view;
    }

    @Override
    public void validateCredentials(String user, String password) {
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
        /**
         * Para acceder a los mensajes de 'errors.xml' es necesario el método 'getResources'
         * Para acceder a ese método necesitamos un objeto que herede de 'Context'
         * De ahí el casting ((Context)view).
         */
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty));
        else if (!password.matches(".*[0-9].*"))
            view.setMessageError(((Context)view).getResources().getString(R.string.password_digit));
        else if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*"))
            view.setMessageError(((Context)view).getResources().getString(R.string.password_case));
        else if (password.length() < 8)
            view.setMessageError(((Context)view).getResources().getString(R.string.password_lenght));
        else{
            //Guardar al ususario en la clase Application.
        }
    }
}
