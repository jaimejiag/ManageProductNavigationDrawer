package com.example.usuario.manageproductsdb.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.usuario.manageproductsdb.R;
import com.example.usuario.manageproductsdb.interfaces.IProduct;
import com.example.usuario.manageproductsdb.interfaces.LoginPresenter;
import com.example.usuario.manageproductsdb.modelo.Product;

public class ManageProductFragment extends Fragment implements LoginPresenter.View {
    LoginPresenter.Presenter presenter;
    EditText edtName;
    EditText edtDescription;
    EditText edtDosage;
    EditText edtBrand;
    EditText edtPrice;
    EditText edtStock;
    Button btnAdd;
    Product product;
    private ManageProductListener mCallBack;

    public interface  ManageProductListener {
        void showListProduct(Bundle bundle);  //Muestra el fragment de añadir o eliminar.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.activity_add_product, container, false);
        return initialize(rootView, savedInstanceState);
    }

    private View initialize(View rootView, Bundle arguments) {
        View view = rootView;
        Bundle bundle;

        //presenter = new ProductPresenterImpl(this);
        edtName = (EditText) rootView.findViewById(R.id.edt_name);
        edtDescription = (EditText) rootView.findViewById(R.id.edt_description);
        edtDosage = (EditText) rootView.findViewById(R.id.edt_dosage);
        edtBrand = (EditText) rootView.findViewById(R.id.edt_brand);
        edtPrice = (EditText) rootView.findViewById(R.id.edt_price);
        edtStock = (EditText) rootView.findViewById(R.id.edt_stock);

        try {
            product = (Product) arguments.getParcelable(IProduct.PRODUCT_KEY);
        } catch (Exception e) {

        }

        if (product != null) {
            edtName.setText(product.getmName());
            edtDescription.setText(product.getmDescription());
            edtDosage.setText(product.getmDosage());
            edtBrand.setText(product.getmBrand());
            edtPrice.setText(String.valueOf(product.getmPrice()));
            edtStock.setText(String.valueOf(product.getmStock()));
        }

        btnAdd = (Button) rootView.findViewById(R.id.btn_add);
        /*btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.validateCredentials(          //validateCredentials también añade el producto, si va bien.
                        edtName.getText().toString(),
                        edtDescription.getText().toString(),
                        edtDosage.getText().toString(),
                        edtBrand.getText().toString(),
                        edtPrice.getText().toString(),
                        edtStock.getText().toString())) {
                    //Intent intent = new Intent(getApplicationContext(), ListProductFragment.class);
                    //startActivity(intent);
                    Intent intent = getIntent();
                    intent.putExtra(IProduct.PRODUCT_KEY, (Parcelable) product);
                    intent.putExtra(IProduct.EDIT_KEY, (Parcelable) new Product(
                            edtName.getText().toString(),
                            edtDescription.getText().toString(),
                            edtDosage.getText().toString(),
                            edtBrand.getText().toString(),
                            Double.parseDouble(edtPrice.getText().toString()),
                            Integer.parseInt(edtStock.getText().toString()),
                            R.drawable.pill
                    ));

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        */

        return view;
    }

    @Override
    public void setMessageError(String error, int viewId) {
        switch (viewId) {
            case R.id.edt_name:
                edtName.setError(error);
                break;
            case R.id.edt_description:
                edtDescription.setError(error);
                break;
            case R.id.edt_dosage:
                edtDosage.setError(error);
                break;
            case R.id.edt_brand:
                edtBrand.setError(error);
                break;
            case R.id.edt_price:
                edtPrice.setError(error);
                break;
            case R.id.edt_stock:
                edtStock.setError(error);
                break;
        }
    }
}
