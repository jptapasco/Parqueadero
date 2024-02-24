package com.example.parqueadero.vendedor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parqueadero.R;

public class MainActivityVendedor extends AppCompatActivity {

    TextView etqNombre;
    TextView etqNit;
    TextView etqDireccion;
    TextView etqTelefono;
    TextView etqNvendedores;
    Intent intent;
    String id_asignacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendedor);

        etqNit = findViewById(R.id.etqNit);
        etqNombre = findViewById(R.id.etqNombre);
        etqDireccion = findViewById(R.id.etqDireccion);
        etqTelefono = findViewById(R.id.etqTelefono);
        etqNvendedores = findViewById(R.id.etqVendedores);

        intent = getIntent();
        id_asignacion = intent.getStringExtra("id_asignacion");
        reemplazarInfo();
    }

    public void reemplazarInfo(){
        String nit = intent.getStringExtra("nit");
        String nombre = intent.getStringExtra("nombre");
        String direccion = intent.getStringExtra("direccion");
        String telefono = intent.getStringExtra("telefono");
        String numVendedores = intent.getStringExtra("numVendedores");

        etqNit.setText(nit);
        etqNombre.setText(nombre);
        etqDireccion.setText(direccion);
        etqTelefono.setText(telefono);
        etqNvendedores.setText(numVendedores);
    }
}