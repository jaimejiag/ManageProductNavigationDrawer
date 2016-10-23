package com.example.usuario.loginrelative.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.loginrelative.ProductApplication;
import com.example.usuario.loginrelative.R;
import com.example.usuario.loginrelative.modelo.Product;

/**
 * Este adapter es el encargado de comunicarse con los datos.
 * Opción un poco más eficiente.
 */

public class ProductAdapterB extends ArrayAdapter<Product> {

    public ProductAdapterB(Context context) {
        super(context, R.layout.item_product, ((ProductApplication)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ImageView imgvProduct;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;

        if (item == null) {
            //Paso 1. Crear un objeto inflater que inicializamos al LayoutInflater del contexto
            LayoutInflater inflater = LayoutInflater.from(getContext());


            //Paso 2. Inflar la vista.Crear en memoria el objeto View que contiene
            //los widget del xml.
            item = inflater.inflate(R.layout.item_product, null);
        }

        //Paso 3. Asignar a las variables los widget mediante el método findViewById.
        imgvProduct = (ImageView)item.findViewById(R.id.imgv_product);
        txvName = (TextView)item.findViewById(R.id.txv_itemName);
        txvStock = (TextView)item.findViewById(R.id.txv_itemStock);
        txvPrice = (TextView)item.findViewById(R.id.txv_itemPrice);

        //Paso 4. Asignar datos del adapter a los widget.
        imgvProduct.setImageResource(getItem(position).getmImage());
        txvName.setText(getItem(position).getmName());
        txvStock.setText(getItem(position).getFotmattedUnitsInStock());
        txvPrice.setText(getItem(position).getFormatedPrice());
        return item;
    }
}
