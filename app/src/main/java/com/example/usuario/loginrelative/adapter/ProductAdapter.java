package com.example.usuario.loginrelative.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.style.BackgroundColorSpan;
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
 * La opción más eficiente.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context) {
        super(context, R.layout.item_product, ((ProductApplication)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ProductHolder productHolder;

        if (item == null) {
            //Paso 1. Crear un objeto inflater que inicializamos al LayoutInflater del contexto
            LayoutInflater inflater = LayoutInflater.from(getContext());


            //Paso 2. Inflar la vista.Crear en memoria el objeto View que contiene
            //los widget del xml.
            item = inflater.inflate(R.layout.item_product, null);
            productHolder = new ProductHolder();

            //Paso 3. Asignar a las variables los widget mediante el método item.findViewById.
            productHolder.imgvProduct = (ImageView) item.findViewById(R.id.imgv_product);
            productHolder.txvName = (TextView) item.findViewById(R.id.txv_itemName);
            productHolder.txvStock = (TextView) item.findViewById(R.id.txv_itemStock);
            productHolder.txvPrice = (TextView) item.findViewById(R.id.txv_itemPrice);

            item.setTag(productHolder);
        } else
            productHolder = (ProductHolder)item.getTag();

        //Paso 4. Asignar datos del adapter a los widget.
        productHolder.imgvProduct.setImageResource(getItem(position).getmImage());
        productHolder.txvName.setText(getItem(position).getmName());
        productHolder.txvStock.setText(getItem(position).getFotmattedUnitsInStock());
        productHolder.txvPrice.setText(getItem(position).getFormatedPrice());

        //Paso 5. Comprueba la posición y cambia el color.
        if (position % 2 == 0)
            item.setBackgroundResource(R.color.colorPar);
        else
            item.setBackgroundResource(R.color.colorImpar);

        return item;
    }

    /**
     * Clase interna que contiene los widget del fichero xml.
     */

    class ProductHolder {
        ImageView imgvProduct;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;
    }
}
