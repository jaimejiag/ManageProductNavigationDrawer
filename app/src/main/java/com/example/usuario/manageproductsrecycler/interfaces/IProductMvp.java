package com.example.usuario.manageproductsrecycler.interfaces;

/**
 * Created by jaime on 23/10/16.
 */

public interface IProductMvp {

    interface View {
        void setMessageError(String error, int viewId);
    }

    interface Presenter {
        boolean validateCredentials(String name, String description, String dosage, String brand,
                                 String price, String stock);
    }
}
