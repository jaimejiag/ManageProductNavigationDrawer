package com.example.usuario.manageproductsfragments.interfaces;

import com.example.usuario.manageproductsfragments.modelo.Product;

import java.security.ProtectionDomain;
import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public interface ProductPresenter {

    void loadProduct();
    Product getProduct(String id);
    void deleteProduct(Product product);
    void onDestroy();

    interface View {
        void showProducts(List<Product> products);

        void showEmptyText(boolean show);

        void showMessage(String message);
    }
}
