package com.example.usuario.manageproductsfragments.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usuario.manageproductsfragments.AccountSettingsActivity;
import com.example.usuario.manageproductsfragments.GeneralSettingsActivity;
import com.example.usuario.manageproductsfragments.R;
import com.example.usuario.manageproductsfragments.adapter.ProductAdapter;
import com.example.usuario.manageproductsfragments.modelo.Product;

public class ListProductFragment extends Fragment {
    public static String PRODUCT_KEY = "product";
    public static String EDIT_KEY = "edit";
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;

    FloatingActionButton fabtnAdd;
    private ProductAdapter adapter;
    private ListView listProduct;
    private int pos;
    private ListProductListener mCallBack;


    public interface  ListProductListener {
        void showManageProduct(Bundle bundle);  //Muestra el fragment de añadir o eliminar.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (ListProductListener) activity;
        } catch (ClassCastException e) {
            throw  new ClassCastException(getContext().toString() + " must implement ListProductListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_listproduct);


        fabtnAdd = (FloatingActionButton) findViewById(R.id.fbtn_list_add);
        fabtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageProductFragment.class);
                startActivityForResult(intent, ADD_PRODUCT);
            }
        });

        listProduct = (ListView) findViewById(R.id.listProduct);
        adapter = new ProductAdapter(this);
        listProduct.setAdapter(adapter);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                pos = position;
                Intent intent = new Intent(ListProductFragment.this, ManageProductFragment.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_PRODUCT);
            }
        });

        //adapter = new ProductAdapterRecycler(this);
        //listProduct = (RecyclerView) findViewById(R.id.rcv_product);
        //listProduct.setLayoutManager(new LinearLayoutManager(this));
        //listProduct.setHasFixedSize(true);
        //listProduct.setAdapter(adapter);
    }

    /*
     * Método que infla el menú en la Activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_add_product:
                intent = new Intent(this, ManageProductFragment.class);
                startActivityForResult(intent, ADD_PRODUCT);
                break;
            case R.id.action_sort_alphabetically:
                adapter.sortAlphabetically();
                break;
            case R.id.acction_settings_general:
                intent = new Intent(ListProductFragment.this,GeneralSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.acction_settings_account:
                intent = new Intent(ListProductFragment.this,AccountSettingsActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ADD_PRODUCT:
                if (resultCode == RESULT_OK){
                    recreate();
                    Product product = (Product)data.getParcelableExtra(PRODUCT_KEY);
                    ((ProductAdapter)listProduct.getAdapter()).addProduct(product);
                }
                break;

            case EDIT_PRODUCT:
                if (resultCode == RESULT_OK) {
                    //adapter.removeProduct((Product)data.getExtras().getParcelable(PRODUCT_KEY));
                    adapter.removeProduct((Product)data.getParcelableExtra(PRODUCT_KEY));
                    adapter.addAt((Product)data.getExtras().getParcelable(EDIT_KEY), pos);
                }
                break;
        }
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.listProduct) {
            menu.setHeaderTitle("Opciones de la lista");
            getMenuInflater().inflate(R.menu.menu_contextual, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_delete_product:
                Bundle bundle = new Bundle();
                bundle.putParcelable(IProduct.PRODUCT_KEY, (Product)listProduct.getItemAtPosition(info.position));
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "SimpleDialog");
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    */
}
