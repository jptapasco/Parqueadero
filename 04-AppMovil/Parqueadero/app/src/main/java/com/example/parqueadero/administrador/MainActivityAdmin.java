package com.example.parqueadero.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parqueadero.MainActivity;
import com.example.parqueadero.R;

public class MainActivityAdmin extends AppCompatActivity {
    Button btnParqueadero;
    Button btnVendedor;
    Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        btnParqueadero = findViewById(R.id.btn_parqueadero);
        btnVendedor = findViewById(R.id.btn_vendedores);
        btnSalir = findViewById(R.id.btn_salir);

        btnParqueadero.setEnabled(false);

        btnVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
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

}