package com.example.usuario.manageproductsfragments;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.manageproductsfragments.fragments.ListProductFragment;
import com.example.usuario.manageproductsfragments.interfaces.SignUpPresenter;
import com.example.usuario.manageproductsfragments.presenter.SignUpPresenterImpl;

public class SignUpActivity extends AppCompatActivity implements SignUpPresenter.View {

    private Button btnSignUp;
    private Spinner spnCounty;
    private Spinner spnCity;
    private RadioGroup rgrpIndividualEnterprise;
    private EditText edtEnterpriseName;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private SignUpPresenterImpl presenter;
    private ViewGroup layout;
    private EditText edtUser;
    private EditText edtPassword;
    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUser = (EditText) findViewById(R.id.edt_userName);
        edtPassword = (EditText) findViewById(R.id.edt_userPassword);
        edtEmail = (EditText) findViewById(R.id.edt_userEmail);
        layout = (RelativeLayout) findViewById(R.id.activity_sign_up);
        edtEnterpriseName = (EditText) findViewById(R.id.edt_enterpriseName);
        spnCounty = (Spinner) findViewById(R.id.spn_provincia);
        spnCity = (Spinner) findViewById(R.id.spn_localidad);
        presenter = new SignUpPresenterImpl(this);

        initRadioClient();
        loaderSpinnerCounty();
    }

    public void signUp(View view){
        //Recoger los datos de la vista y llama al método del presentador.
        presenter.validateCredentials(
               edtUser.getText().toString(),
               edtPassword.getText().toString(),
                edtEmail.getText().toString()
        );
    }

    private void showCompany(boolean b) {
        edtEnterpriseName.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    private void loaderSpinnerCounty(){
        //Inicializar el spinner Provincias.
        //Le pasas el contexto, el array con los datos y por último el layout como se visualizará, en este caso cogemos una predefinido en android.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_dropdown_item);
        spnCounty.setAdapter(adapter);

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()){
                    case R.id.spn_provincia:
                        loaderSpinnerCity(position);
                        break;

                    case R.id.spn_localidad:
                        showCitySelected();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spnCounty.setOnItemSelectedListener(spinnerListener);
        spnCity.setOnItemSelectedListener(spinnerListener);
    }

    private void loaderSpinnerCity(int position) {
        Resources resources = getResources();
        TypedArray typedArray = resources.obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] citys = typedArray.getTextArray(position);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, citys);
        spnCity.setAdapter(adapter);
    }

    public void showCitySelected(){
        Toast.makeText(getApplicationContext(),
                getString(R.string.message_county_city,
                        spnCounty.getSelectedItem().toString(),
                        spnCity.getSelectedItem().toString()),
                Toast.LENGTH_SHORT).show();
    }

    private void initRadioClient(){
        rgrpIndividualEnterprise = (RadioGroup) findViewById(R.id.rgrp_individualEnterprise);
        rgrpIndividualEnterprise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtn_individual:
                        showCompany(false);
                        break;

                    case R.id.rbtn_enterprise:
                        showCompany(true);
                        break;
                }
            }
        });
    }

    /**
     * Método que muestra un mensaje personalizado en un componente {@link Snackbar} según el error.
     * @param nameResource El nombre del String del mensaje de error a mostrar
     * {@link android.content.res.Resources#getIdentifier(String, String, String)} para obtener el id en la clase R.
     * @param viewId el id de la vista donde se mostrará el error.
     */
    @Override
    public void setMessageError(String nameResource, int viewId) {
        String messageError = getString(getResources().getIdentifier(nameResource, "string", getPackageName()));


        switch (viewId){
            case R.id.til_userName:
                //edtUser.setError(messageError);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.til_userPassword:
                //edtPassword.setError(messageError);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case  R.id.til_userEmail:
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    public void startActivity () {
        Intent intent = new Intent(SignUpActivity.this, ListProductFragment.class);
        startActivity(intent);
        finish();
    }
}
