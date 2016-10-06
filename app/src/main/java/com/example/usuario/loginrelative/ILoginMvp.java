package com.example.usuario.loginrelative;

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
        public void setMessageError(String error);
    }

    interface Presenter {
        public void validateCredentials(String user, String password);
    }
}
