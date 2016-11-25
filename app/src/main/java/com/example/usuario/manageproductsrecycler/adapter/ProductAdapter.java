package com.example.usuario.manageproductsrecycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.manageproductsrecycler.R;
import com.example.usuario.manageproductsrecycler.aplications.ProductApplication;
import com.example.usuario.manageproductsrecycler.modelo.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 18/11/16.
 */

/**
 * No es necesario llamar a este método notifyDataSetChanged() después de
 * add(), insert(), remove(), clear(), sort(), ... porque estos métodos
 * llaman automáticamente setNotifyOnChange=true y se utiliza la copia local.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private boolean ASC;

    /**
     * Se pasa como tercer parámetro en la llamada a super un ArrayList
     * con los elementos del Repositorio. Se tiene una copia local diferente
     * al repositorio.
     * @param context
     */
    public ProductAdapter(Context context) {
        super (context, R.layout.item_product, new ArrayList<Product>(((ProductApplication)context.getApplicationContext()).getProducts()));

        this.context = context;
        ASC = true;
    }

    //Si en el examen pide que se muestre tal producto con X nombre o cualquier filtrado, este es
    //el método donde se comprueba, getView().
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ProductHolder productHolder;

        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_product,null);
            productHolder = new ProductHolder();

            productHolder.productImage = (ImageView)view.findViewById(R.id.imgv_product);
            productHolder.txvName = (TextView)view.findViewById(R.id.txv_itemName);
            productHolder.txvStock = (TextView)view.findViewById(R.id.txv_itemStock);
            productHolder.txvPrice = (TextView)view.findViewById(R.id.txv_itemPrice);

            view.setTag(productHolder);
        } else
            productHolder = (ProductHolder)view.getTag();

        productHolder.productImage.setImageResource(getItem(position).getmImage());
        productHolder.txvName.setText(getItem(position).getmName());
        productHolder.txvStock.setText(getItem(position).getFotmattedUnitsInStock());
        productHolder.txvPrice.setText(getItem(position).getFormatedPrice());

        return view;
    }

    class ProductHolder {
        ImageView productImage;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;
    }

    public void sortAlphabetically() {
        ASC = !ASC;

        if (ASC)
            sort(Product.NAME_COMPARATOR);
        else
            sort(Collections.reverseOrder());

        //notifyDataSetChanged();   //No hace falta notificarlo porque lo hace solo.
    }

    public void addProduct(Product product) {
        add(product);
    }

    public void removeProduct(Product product){
        remove(product);
        notifyDataSetChanged();
    }

    public void addAt(Product product, int position){
        insert(product, position);
        notifyDataSetChanged();
    }
}
