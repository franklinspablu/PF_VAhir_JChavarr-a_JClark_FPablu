package com.example.semestral_hpa.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.semestral_hpa.Models.User;
import com.example.semestral_hpa.R;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaListViewAdapter extends ArrayAdapter {

    List<User> opciones = new ArrayList();

    public AsistenciaListViewAdapter(@NonNull Context context, @NonNull List<User> datos) {
        super(context, R.layout.listview_template_asistencia, datos);

        opciones = datos;
    }

    public View getView(int pos, View v, ViewGroup vgroup){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_template_asistencia,null);

        TextView nombre = item.findViewById(R.id.lblNombre);
        nombre.setText(opciones.get(pos).getNombres()+" "+opciones.get(pos).getApellidos());

        TextView cedula = item.findViewById(R.id.lblCedula);
        cedula.setText(opciones.get(pos).getCedula());

        TextView hora = item.findViewById(R.id.lblHora);
        hora.setText(opciones.get(pos).getCreatedAt());

        return item;
    }
}