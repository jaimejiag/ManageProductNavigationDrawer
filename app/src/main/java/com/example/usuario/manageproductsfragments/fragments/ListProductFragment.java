package com.example.usuario.manageproductsfragments.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.usuario.manageproductsfragments.R;
import com.example.usuario.manageproductsfragments.adapter.ProductAdapter;
import com.example.usuario.manageproductsfragments.interfaces.IProduct;
import com.example.usuario.manageproductsfragments.interfaces.LoginPresenter;
import com.example.usuario.manageproductsfragments.interfaces.ProductPresenter;
import com.example.usuario.manageproductsfragments.modelo.Product;
import com.example.usuario.manageproductsfragments.presenter.ProductPresenterImpl;

import java.util.List;
import java.util.Objects;

public class ListProductFragment extends Fragment implements  ProductPresenter.View{
    FloatingActionButton fabtnAdd;
    private ProductAdapter adapter;
    private ListView listProduct;
    private int pos;
    private ListProductListener mCallBack;
    private ProductPresenter presenter;
    private TextView emptyProduct;


    public interface ListProductListener {
        void showManageProduct(Bundle bundle);  //Muestra el fragment de añadir o eliminar.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductAdapter(getContext());
        presenter = new ProductPresenterImpl(this);
        setRetainInstance(true);

        //Esta acción le dice a la activity que el fragment tiene su propio menú y llama al método
        //callback onCreateOptionMenu().
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        adapter = null;
        presenter = null;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_listproduct, container, false);



        listProduct = (ListView) rootView.findViewById(R.id.listProduct);
        listProduct.setAdapter(adapter);
        listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(IProduct.PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                pos = position;
                mCallBack.showManageProduct(bundle);
            }
        });

        fabtnAdd = (FloatingActionButton) rootView.findViewById(R.id.fbtn_list_add);
        fabtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.showManageProduct(null);
            }
        });

        emptyProduct = (TextView) rootView.findViewById(R.id.empty);

        return rootView;
    }

    /*
     * Método que infla el menú en el fragment
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_listproduct, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_sort_alphabetically:
                adapter.sortAlphabetically();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showProducts(List<Product> products) {
        adapter.updateProducts(products);
    }

    private void hideList(boolean hide) {
        listProduct.setVisibility(hide ? View.GONE : View.VISIBLE);
        emptyProduct.setVisibility(hide ? View.VISIBLE : View.GONE);
    }

    public void showEmptyText(boolean show) {
        hideList(show);
    }

    public void showMessage(String message) {

    }

    public void deleteObject(Object object) {

    }
}
