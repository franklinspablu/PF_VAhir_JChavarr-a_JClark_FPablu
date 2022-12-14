package com.example.semestral_hpa;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.example.semestral_hpa.Adaptadores.AsistenciaListViewAdapter;


import java.util.ArrayList;
import java.util.List;

public class ActivityLstAsistencia extends AppCompatActivity {
    ListView lstAsistencia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asistencia);

        lstAsistencia= (ListView)findViewById(R.id.lstAsistencia);

        this.LlenarListview();

    }

    private void LlenarListview() {
        String datos;
       // List<Estudiantes> estudiantes = ConvertArchivoToList(datos);

       // AsistenciaListViewAdapter adapter = new AsistenciaListViewAdapter(getApplicationContext(),estudiantes);
      //  lstAsistencia.setAdapter(adapter);
    }

    private List<Estudiante> ConvertArchivoToList(String datos) {
        List<Estudiante> lstEstudiantes = new ArrayList<>();
        String[] estudiantes = datos.split("\\~");
        for (String estudiante : estudiantes){
            String[] campos = estudiante.split("\\|");
            Estudiante est = new Estudiante(
                    campos[0].substring(3),
                    "",
                    campos[2].substring(3),
                    campos[3].substring(3)
            );
            lstEstudiantes.add(est);
        }

        return lstEstudiantes;
    }
}
