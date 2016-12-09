package com.example.usuario.manageproductsfragments.presenter;

import com.example.usuario.manageproductsfragments.aplications.ProductRepository;
import com.example.usuario.manageproductsfragments.interfaces.ProductPresenter;
import com.example.usuario.manageproductsfragments.modelo.Product;

/**
 * Created by jaime on 23/10/16.
 */

public class ProductPresenterImpl implements ProductPresenter {
    private ProductPresenter.View view;
    private ProductRepository repository;


    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view = view;
        repository = new ProductRepository();
    }

    @Override
    public void loadProduct() {
        if (repository.getProducts().isEmpty())
            view.showEmptyText(true);
        else
            view.showProducts(repository.getProducts());
    }

    @Override
    public Product getProduct(String id) {
        //return repository.getProduct(id); Hay que implementarlo todavía.
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);

        //Depende de la implementación.
        loadProduct();

        /*view.getAdapter().deleteProduct();
        if (view.getAdapater().isEmpty())
            view.showEmptyText(true);
        */
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
