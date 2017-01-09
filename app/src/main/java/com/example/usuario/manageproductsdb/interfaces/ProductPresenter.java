package com.example.usuario.manageproductsdb.interfaces;

import com.example.usuario.manageproductsdb.modelo.Product;

import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public interface ProductPresenter {

    void loadProduct();
    Product getProduct(String id);
    void deleteProduct(Product product);
    //void deleteFinallyProduct(Product product);
    void addProduct(Product product);
    void onDestroy();

    interface View {
        void showProducts(List<Product> products);

        void showEmptyText(boolean show);

        void showMessage(String message);

        void showMessageDelete(Product product);
    }
}
