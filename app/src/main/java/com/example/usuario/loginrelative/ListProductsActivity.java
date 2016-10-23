package com.example.usuario.loginrelative;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.example.usuario.loginrelative.adapter.ProductAdapter;
import com.example.usuario.loginrelative.adapter.ProductAdapterA;
import com.example.usuario.loginrelative.adapter.ProductAdapterB;

public class ListProductsActivity extends ListActivity {

    //private ArrayAdapter<Product> adapter;
    FloatingActionButton fabtnAdd;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        //ListView = (ListView) findViewById (R.id.lvProduct); Esto ya no se hace falta gracias a que hemos
        //inflado la clase con ListActivity

        fabtnAdd = (FloatingActionButton) findViewById(R.id.fabtn_add);
        fabtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);
            }
        });

        //CASO 1: Adapter no personalizado.
        //adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, ((ProductApplication)getApplication()).getProducts());

        //CASO 2: Personalizado.
        adapter = new ProductAdapter(this);  //Cambiar entre ProductAdapterA y B para hacer pruebas de velocidad. OJO cambialo al declararlo también.
        getListView().setAdapter(adapter);
    }
}
