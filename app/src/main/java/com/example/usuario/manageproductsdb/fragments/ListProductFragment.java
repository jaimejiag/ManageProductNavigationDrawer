package com.example.usuario.manageproductsdb.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.usuario.manageproductsdb.ConfirmDialog;
import com.example.usuario.manageproductsdb.R;
import com.example.usuario.manageproductsdb.adapter.ProductAdapter;
import com.example.usuario.manageproductsdb.interfaces.IProduct;
import com.example.usuario.manageproductsdb.interfaces.ProductPresenter;
import com.example.usuario.manageproductsdb.modelo.Product;
import com.example.usuario.manageproductsdb.presenter.ProductPresenterImpl;

import java.util.List;

public class ListProductFragment extends Fragment implements  ProductPresenter.View{
    FloatingActionButton fabtnAdd;
    private ProductAdapter adapter;
    private ListView listProduct;
    private int pos;
    private ListProductListener mCallBack;
    private ProductPresenter presenter;
    private TextView emptyProduct;
    private PopupMenu popup;
    private AdapterView<?> mItemParent;
    private int mItemPos;
    private static Product p;


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
        super.onDestroy();

        adapter = null;
        presenter = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (ListProductListener) activity;
        } catch (ClassCastException e) {
            throw  new ClassCastException(getContext().toString() + " must implement MultiListProductListener");
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

        listProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItemParent = adapterView;
                mItemPos = i;

                popup  = new PopupMenu(getContext(), view);
                popup.setGravity(Gravity.END);
                popup.getMenuInflater().inflate(R.menu.menu_delete, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        p = (Product) mItemParent.getItemAtPosition(mItemPos);
                        ConfirmDialog dialog = new ConfirmDialog();
                        Bundle zip = new Bundle();
                        zip.putParcelable("product", p);
                        dialog.setPresenter(presenter);
                        dialog.setArguments(zip);
                        dialog.show(getActivity().getSupportFragmentManager(), "");
                        return true;
                    }
                });

                popup.show();
                return true;
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

    //Método que muestra un snackbar con un botón de deshacer la acción de eliminación de producto.
    @Override
    public void showMessageDelete(final Product product) {
        Snackbar.make(getView(), "Producto eliminado", Snackbar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.addProduct(product);
                    }
                }).show();


        /*
        //SETCALLBACK (hacer una llamada a un método callback de un Snackbar.
        //incluso si el Snackbar se ha eliminado mediante Swipe.
        .setCallback(new Snackbar.Callback(){

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);

                if (event == DISMISS_EVENT_TIMEOUT || event == DISMISS_EVENT_SWIPE)
                    presenter.deleteFinallyProduct(product);
            }
        }).show();
        */
    }
    public void deleteObject(Object object) {

    }


}
