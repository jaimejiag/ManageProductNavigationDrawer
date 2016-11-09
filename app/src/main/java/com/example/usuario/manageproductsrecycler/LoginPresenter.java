package com.example.usuario.manageproductsrecycler;

import android.content.Context;
import android.text.TextUtils;

import com.example.usuario.manageproductsrecycler.interfaces.ILoginMvp;

/**
 * Created by usuario on 6/10/16.
 */

public class LoginPresenter implements ILoginMvp.Presenter {

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view) {
        this.view = view;
    }

    @Override
    public boolean validateCredentials(String user, String password) {
        boolean result = false;

        if (TextUtils.isEmpty(user))
        /**
         * Para acceder a los mensajes de 'errors.xml' es necesario el método 'getResources'
         * Para acceder a ese método necesitamos un objeto que herede de 'Context'
         * De ahí el casting ((Context)view).
         */
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_user);
        else if (TextUtils.isEmpty(password))
            view.setMessageError(((Context)view).getResources().getString(R.string.data_empty), R.id.edt_password);
        else if (!password.matches(".*[0-9].*"))
            view.setMessageError(((Context)view).getResources().getString(R.string.password_digit), R.id.edt_password);
        else if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*"))
            view.setMessageError(((Context)view).getResources().getString(R.string.password_case), R.id.edt_password);
        else if (password.length() < 8)
            view.setMessageError(((Context)view).getResources().getString(R.string.password_lenght), R.id.edt_password);
        else{
            //Guardar al ususario en la clase Application.
            /**
             * ((Context)view) -> Contexto de la actividad
             * ((Context)view).GetApplicationContext() -> Objeto de la aplicación.
             * ((LoginAplication)((Context)view).getAplicationContext()) -> Objeto LoginApplication
             */
            //((LoginAplication)((Context)view).getApplicationContext()).setUser(new User(user, password));
            result = true;
        }

        return result;
    }
}
