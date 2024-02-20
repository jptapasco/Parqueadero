package com.example.parqueadero.administrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.parqueadero.MainActivity;
import com.example.parqueadero.R;
import com.example.parqueadero.vendedor.MainActivityVendedor;

public class Vendedores extends AppCompatActivity {
    Button btnParqueadero;
    Button btnVendedor;
    Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedores);

        btnParqueadero = findViewById(R.id.btn_parqueadero);
        btnVendedor = findViewById(R.id.btn_vendedores);
        btnSalir = findViewById(R.id.btn_salir);

        btnVendedor.setEnabled(false);

        btnParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
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