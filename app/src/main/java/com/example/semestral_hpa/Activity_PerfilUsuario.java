package com.example.semestral_hpa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_PerfilUsuario extends AppCompatActivity {
    TextView txtTitulo;
    String name;
    int id, role;

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
    public void GetAsistencia(View v){}








}
