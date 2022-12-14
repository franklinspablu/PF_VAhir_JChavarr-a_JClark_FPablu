package com.example.semestral_hpa;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestral_hpa.Helpers.RegisterRequest;
import com.example.semestral_hpa.Helpers.RegisterResponse;
import com.example.semestral_hpa.Services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySignIn extends AppCompatActivity {

    TextView tbname, tbLastName, tbId, tbEmail, tbPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        InicializarControles();
    }


    private void InicializarControles() {
    tbname = findViewById(R.id.tbName);
    tbLastName = findViewById(R.id.tbLastName);
    tbId = findViewById(R.id.tbId);
    tbEmail = findViewById(R.id.tbEmail);
    tbPwd = findViewById(R.id.tbPwd);

    }



    public void Register(View v){

        if(tbname.getText().toString().isEmpty() || tbLastName.getText().toString().isEmpty() || tbId.getText().toString().isEmpty() || tbEmail.getText().toString().isEmpty() || tbPwd.getText().toString().isEmpty()){
            Toast.makeText(ActivitySignIn.this,"Campos requerido vacíos", Toast.LENGTH_LONG).show();
        } else {

            try {
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setNombre(tbname.getText().toString());
                registerRequest.setApellido(tbLastName.getText().toString());
                registerRequest.setCedula(tbId.getText().toString());
                registerRequest.setCorreo(tbEmail.getText().toString());
                registerRequest.setContrasena(tbPwd.getText().toString());

                Call<RegisterResponse> RegisterResponseCall = ApiClient.getUserService().createUser(registerRequest);
                RegisterResponseCall.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        if (response.isSuccessful()) {
                            Toast.makeText(ActivitySignIn.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                            RegisterResponse RegisterResponse = response.body();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ActivitySignIn.this, "data: " + RegisterResponse.getMensaje(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ActivitySignIn.this, ActivityLogin.class));
                                }
                            }, 700);

                        } else {
                            Toast.makeText(ActivitySignIn.this, "Registro fallido", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(ActivitySignIn.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }catch (Exception e){
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();

            }

        }

    }


}
