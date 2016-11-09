package com.example.usuario.manageproductsrecycler;

import android.app.Application;

import com.example.usuario.manageproductsrecycler.modelo.Product;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by usuario on 20/10/16.
 */

public class ProductApplication extends Application {
    private ArrayList<Product> products = new ArrayList();

    @Override
    public void onCreate() {
        saveProduct(new Product("ABILIFY", "5 MG 28 COMPRIMIDOS", "5 mg", "BRISTOL MYERS SQUIBB", 132.79, 10, R.drawable.pill));
        saveProduct(new Product("BETADINE", "10% 5 MONODOSIS 10 MG", "10 MG", "MEDA PHARMA SAU", 5.87, 150, R.drawable.pill));
        saveProduct(new Product("DAIVONEX", "0.005% SOLUCION CUTANEA 60 ML", "60 ML", "LEO PHARMA", 24.13, 89, R.drawable.pill));
        saveProduct(new Product("HEMOAL", "POMADA 50 G", "50 G", "COMBE EUROPA", 7.65, 270, R.drawable.pill));
        saveProduct(new Product("NOLOTIL", "2 G 5 AMPOLLAS 5 M", "2 G", "BOEHRINGER INGELHEIM ESP", 2.48, 132, R.drawable.pill));
        saveProduct(new Product("SIBELIUM", "5 MG 30 COMPRIMIDOS", "5 MG", "ESTEVE", 4.92, 53, R.drawable.pill));
        saveProduct(new Product("VASONASE", "RETARD 40 MG 60 CAPSULAS", "40 MG", "ASTELLAS PHARMA", 18.81, 28, R.drawable.pill));
    }

    public void saveProduct (Product product) {
        products.add(product);
    }

    public ArrayList<Product> getProducts(boolean ascendente){
        //El segundo argumento es cómo va a compararse, cambiar entre las dos constantes Comparator creados para ordenar de manera distinta.
        //Collections.sort(products, Product.PRICE_COMPARATOR);
        //Collections.sort(products, (p1, p2) -> Double.compare(p1.getmPrice(), p2.getmPrice()));
        if (!ascendente)
            Collections.sort(products, Collections.reverseOrder());
        else
            Collections.sort(products);
        return products;
    }
}