package com.example.semestral_hpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListaGrupo_Act extends AppCompatActivity {

    ListView lstAsistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_asistencia);

        lstAsistencia= (ListView)findViewById(R.id.lstAsistencia);


    }


}
