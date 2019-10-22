package com.dpridoy.mvploginpage.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dpridoy.mvploginpage.Contract;
import com.dpridoy.mvploginpage.LoginPresenter;
import com.dpridoy.mvploginpage.R;
import com.dpridoy.mvploginpage.data.LoginCredentials;

public class MainActivity extends AppCompatActivity implements Contract.LoginView {

    private ProgressDialog progressDialog;
    private LoginPresenter presenter;
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);

        presenter=new LoginPresenter(this);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString=email.getText().toString();
                String passwordString=password.getText().toString();

                LoginCredentials credentials=new LoginCredentials(emailString,passwordString);
                presenter.start(credentials);
            }
        });
    }

    @Override
    public void showProgressbar() {
        progressDialog.show();
    }

    @Override
    public void hideProgressbar() {
        progressDialog.dismiss();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this,"Login success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
