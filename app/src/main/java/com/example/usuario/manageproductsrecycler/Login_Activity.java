package com.example.usuario.manageproductsrecycler;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.manageproductsrecycler.interfaces.IValidateAccount;
import com.example.usuario.manageproductsrecycler.interfaces.IValidateUser;
import com.example.usuario.manageproductsrecycler.presenter.LoginPresenter;

/**
 * This class operate the classic login screen using MVP method.
 * @Author Jaime
 * @Version 1.0
 */

public class Login_Activity extends AppCompatActivity implements IValidateAccount.View {

    private IValidateAccount.Presenter loginMvp;
    private EditText edtPassword;
    private EditText edtUser;
    private Button btnOk;
    private Button btnSignUp;
    private final String TAG = "loginrelative";
    private ViewGroup layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        layout = (ViewGroup) findViewById(R.id.activity_login_relative_);
        loginMvp = new LoginPresenter(this);
        edtUser = (EditText) findViewById(R.id.edt_user);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginPresenter)loginMvp).validateCredentialsLogin(edtUser.getText().toString(), edtPassword.getText().toString());
            }
        });

        btnSignUp = (Button) findViewById(R.id.btn_signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        //Comprobar la persistencia del objeto Application
        //if (((LoginAplication)getApplicationContext()).getUser() != null)
        //    Log.d("TAG", ((LoginAplication)getApplicationContext()).getUser().toString());
    }

    private void resetValues() {
        edtUser.setText("");
        edtPassword.setText("");
    }

    @Override
    public void setMessageError(String nameResource, int idView) {
        //Se tiene que recoger el recurso cuyo nombre sea el que se pasa en nameResource.
        String messageError = getString(getResources().getIdentifier(nameResource, "string", getPackageName()));
        switch (idView){
            case R.id.til_user:
                //edtUser.setError(messageError);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.til_password:
                //edtPassword.setError(messageError);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Activity finalizada");
    }
}
