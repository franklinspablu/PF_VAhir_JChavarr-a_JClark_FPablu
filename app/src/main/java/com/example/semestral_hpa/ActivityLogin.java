package com.example.semestral_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.semestral_hpa.Helpers.LoginRequest;
import com.example.semestral_hpa.Helpers.LoginResponse;
import com.example.semestral_hpa.Services.ApiClient;

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

                if(response.isSuccessful() && response.body()!= null){
                    Toast.makeText(ActivityLogin.this,"Login Successful", Toast.LENGTH_LONG).show();
                    String nombre = response.body().getUsuario().getNombres()+ " "+response.body().getUsuario().getApellidos();
                    int rol = response.body().getUsuario().getRole();
                    int id=0;

                   id = rol == 2 ? response.body().getUsuario().getDocenteId() :  response.body().getUsuario().getEstudianteId();


                    Bundle b = new Bundle();
                    b.putString("nombre", nombre);
                    b.putInt("id", id);
                    b.putInt("rol", rol);
                    Intent i = new Intent(getApplicationContext(), Activity_PerfilUsuario.class);
                    i.putExtras(b);
                    startActivity(i);

                        }else{
                    Toast.makeText(ActivityLogin.this,"Login Failed", Toast.LENGTH_LONG).show();
                 }
                }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }

}




