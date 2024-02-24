package com.example.parqueadero.vendedor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parqueadero.MainActivity;
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

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);

        btnParqueaderoV.setEnabled(false);
        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Entrada.class);
                startActivity(intencion);
            }
        });
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Historial.class);
                startActivity(intencion);
            }
        });
        btnTarifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Tarifas.class);
                startActivity(intencion);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intencion);
            }
        });
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