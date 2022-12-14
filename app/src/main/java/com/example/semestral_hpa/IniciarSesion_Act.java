package com.example.semestral_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class IniciarSesion_Act extends AppCompatActivity {
//iniciar sesion

    TextView txtCorreo, txtConstrasena;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

    }


    private void InicializarControles(){

        txtCorreo = findViewById(R.id.txtcorreo);
        txtConstrasena  = findViewById(R.id.txtcontraseña);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtCorreo.getText().toString()) || TextUtils.isEmpty(txtConstrasena.getText().toString())){
                    Toast.makeText(IniciarSesion_Act.this,"Correo / Contraseña Requerida", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    login();
                }
            }
        });
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(txtCorreo.getText().toString());
        loginRequest.setPassword(txtConstrasena.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(IniciarSesion_Act.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(IniciarSesion_Act.this, "" , Toast.LENGTH_SHORT).show();
                           // startActivity(new Intent(IniciarSesion_Act.this,DashboardActivity.class).putExtra("data",LoginResponse.));
                        }
                    },700);

                }else{
                    Toast.makeText(IniciarSesion_Act.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(IniciarSesion_Act.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}




}