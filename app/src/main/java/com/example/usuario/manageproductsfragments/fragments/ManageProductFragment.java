package com.example.usuario.manageproductsfragments.fragments;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.usuario.manageproductsfragments.R;
import com.example.usuario.manageproductsfragments.interfaces.IProduct;
import com.example.usuario.manageproductsfragments.interfaces.LoginPresenter;
import com.example.usuario.manageproductsfragments.modelo.Product;
import com.example.usuario.manageproductsfragments.presenter.ProductPresenterImpl;

import static com.example.usuario.manageproductsfragments.fragments.ListProductFragment.EDIT_KEY;
import static com.example.usuario.manageproductsfragments.fragments.ListProductFragment.PRODUCT_KEY;

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
        setContentView(R.layout.activity_add_product);

        initialize();
    }

    private void initialize() {
        presenter = new ProductPresenterImpl(this);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        edtDosage = (EditText) findViewById(R.id.edt_dosage);
        edtBrand = (EditText) findViewById(R.id.edt_brand);
        edtPrice = (EditText) findViewById(R.id.edt_price);
        edtStock = (EditText) findViewById(R.id.edt_stock);

        try {
            product = (Product) getIntent().getExtras().getSerializable(IProduct.PRODUCT_KEY);
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

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
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
