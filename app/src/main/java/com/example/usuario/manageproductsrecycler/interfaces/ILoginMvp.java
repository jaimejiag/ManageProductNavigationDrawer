package com.example.usuario.manageproductsrecycler.interfaces;

/**
 * Created by usuario on 6/10/16.
 */
public interface ILoginMvp {

    //No hace falta poner 'public static final' porque es redundante en una interface
    int PASSWORD_DIGIT = 1;
    int PASSWORD_CASE = 2;
    int PASSWORD_LENGHT = 3;
    int DATA_EMPTY = 4;

    interface  View {
        void setMessageError(String error, int viewId);
    }

    interface Presenter {
        boolean validateCredentials(String user, String password);
    }
}
