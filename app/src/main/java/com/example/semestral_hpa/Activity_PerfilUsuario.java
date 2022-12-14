package com.example.semestral_hpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestral_hpa.Adaptadores.AsistenciaListViewAdapter;
import com.example.semestral_hpa.Helpers.EstudiantesRequest;
import com.example.semestral_hpa.Helpers.EstudiantesResponse;
import com.example.semestral_hpa.Helpers.LoginRequest;
import com.example.semestral_hpa.Helpers.LoginResponse;
import com.example.semestral_hpa.Services.ApiClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_PerfilUsuario extends AppCompatActivity {
    TextView txtTitulo;
    String name, correo;
    int id, role;
    ListView listView_template_asistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent i = getIntent();
        Bundle b = getIntent().getExtras();

        if (b!=null){
            this.LoadTextViewBundle(b);
        }else{
            this.LoadTextView();
        }

        InicializarControles();
    }

    private void InicializarControles() {
        txtTitulo = (TextView) findViewById(R.id.txtTitle);
        txtTitulo.setText(name);
    }

    private void LoadTextView() {
        txtTitulo.setText(" ");
    }

    private void LoadTextViewBundle(Bundle b) {
        name = "Bienvenido " + b.getString("nombre");
        id = b.getInt("id");
        role = b.getInt("rol");
        correo = b.getString("correo");
        try {
            switch (role){
                case 1:
                    setContentView(R.layout.activity_admin);
                    break;
                case 2:
                    setContentView(R.layout.activity_profesor);
                    break;

                case 3:
                    setContentView(R.layout.activity_estudiante);
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this, "Role: " + role + " Error: " + e, Toast.LENGTH_SHORT).show();
        }
    }


    public void ViewProfile(View v){}

    //obtener los cursos a los que le da clase el profesor
    public void GetListAsignaturas(View v){}


    //estudiante ve asistencia por asignatura
    public void GetListAsistencia(View v){}


    //obtener lista de asistencia x asignatura
    public void GetAsistencia(View v){
       EstudiantesRequest eRequest = new EstudiantesRequest();
        eRequest.setGaId(id);

        Call<EstudiantesResponse> loginResponseCall = ApiClient.getUserService().getEstudiantesPorGrupo(eRequest);

        loginResponseCall.enqueue(new Callback<EstudiantesResponse>() {
            @Override
            public void onResponse(Call<EstudiantesResponse> call, Response<EstudiantesResponse> response) {

                if(response.isSuccessful() && response.body()!= null){

                    List<EstudiantesResponse> responseList;
                    responseList = Collections.singletonList(response.body());
                    String nombre = response.body().getName();
                    int rol = role;
                    int id= response.body().getStudentId();

                    AsistenciaListViewAdapter adapter = new AsistenciaListViewAdapter(this, responseList);
                    listView_template_asistencia.setAdapter(adapter);


                    Bundle b = new Bundle();
                    b.putString("nombre", nombre);
                    b.putInt("id", id);
                    b.putInt("rol", rol);
                    Intent i = new Intent(getApplicationContext(), Activity_PerfilUsuario.class);
                    i.putExtras(b);
                    startActivity(i);

                }else{
                    Toast.makeText(Activity_PerfilUsuario.this,"Login Failed", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<EstudiantesResponse> call, Throwable t) {

            }

        });
    }








}
