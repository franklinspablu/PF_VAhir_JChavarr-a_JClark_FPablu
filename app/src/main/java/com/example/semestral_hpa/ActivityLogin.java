package com.example.semestral_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.semestral_hpa.Helpers.LoginRequest;
import com.example.semestral_hpa.Helpers.LoginResponse;
import com.example.semestral_hpa.Models.User;
import com.example.semestral_hpa.Services.ApiClient;

import java.util.List;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
//iniciar sesion

    TextView txtCorreo, txtConstrasena, txtRegistrarse;
    Button btnIngresar;
    LoginResponse loginResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InicializarControles();
    }


    private void InicializarControles(){

        txtCorreo = findViewById(R.id.txtcorreo);
        txtConstrasena  = findViewById(R.id.txtcontraseña);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtRegistrarse = findViewById(R.id.txtRegistrarse);

    }

    public void RegistrarseScreen(View v){
        startActivity(new Intent(this, ActivitySignIn.class));
    }


    public void IngresarOnClick(View v){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(txtCorreo.getText().toString());
        loginRequest.setPassword(txtConstrasena.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(ActivityLogin.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switch (loginResponse.getRole()){

                                case 1:{
                                    Toast.makeText(ActivityLogin.this, "Eres Admin, Benvenute", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(IniciarSesion_Act.this,Admin_Act.class).putExtra("data",loginResponse.getNombres()));
                                }

                                case 2: {
                                    Toast.makeText(ActivityLogin.this, "Eres Profe, Benvenute", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(IniciarSesion_Act.this,Estudiante_Act.class).putExtra("data",loginResponse.getNombres()));
                                }

                                case 3: {
                                    Toast.makeText(ActivityLogin.this, "Eres Estudiante, Benvenute", Toast.LENGTH_SHORT).show();
                                   // startActivity(new Intent(IniciarSesion_Act.this, Profesor_Act.class).putExtra("data",loginResponse.getNombres()));
                                }
                            }

                        }
                    },700);

                }else{
                    Toast.makeText(ActivityLogin.this,"Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(ActivityLogin.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}




