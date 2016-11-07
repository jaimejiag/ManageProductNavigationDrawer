package com.example.usuario.manageproductsrecycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.manageproductsrecycler.ProductApplication;
import com.example.usuario.manageproductsrecycler.R;
import com.example.usuario.manageproductsrecycler.modelo.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Este adapter es el encargado de comunicarse con los datos.
 * La opción más eficiente.
 */

public class ProductAdapterRecycler extends RecyclerView.Adapter<ProductAdapterRecycler.ProductViewHolder>{


    private ArrayList<Product> products;
    private Context context;
    private static boolean ASC = true;

    public ProductAdapterRecycler(Context context) {
        this.context = context;
        products = new ArrayList<Product>(((ProductApplication)context.getApplicationContext()).getProducts(true));
    }


    //Por cada item se llamará e inflará la vista.
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null); //Si no es null coge espacios en blanco.
        return new ProductViewHolder(item);
    }

    //Asigna los valores por cada item de List
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.imgvProduct.setImageResource(products.get(position).getmImage());
        holder.txvName.setText(products.get(position).getmName());
        holder.txvStock.setText(products.get(position).getFotmattedUnitsInStock());
        holder.txvPrice.setText(products.get(position).getFormatedPrice());
    }

    //Contará los items en List
    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgvProduct;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;

        public ProductViewHolder(View item) {
            super(item);
            imgvProduct = (ImageView) item.findViewById(R.id.imgv_product);
            txvName = (TextView) item.findViewById(R.id.txv_itemName);
            txvStock = (TextView) item.findViewById(R.id.txv_itemStock);
            txvPrice = (TextView) item.findViewById(R.id.txv_itemPrice);
        }
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public void sortAlphabetically(){
        ASC = !ASC;
        products.clear();
        products.addAll(((ProductApplication)context.getApplicationContext()).getProducts(ASC));
        notifyDataSetChanged();     //Notificación a la vista. Patrón Observable-Observador.
    }
}