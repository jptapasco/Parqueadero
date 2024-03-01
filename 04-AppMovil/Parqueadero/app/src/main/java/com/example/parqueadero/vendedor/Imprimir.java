package com.example.parqueadero.vendedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parqueadero.R;
import com.example.parqueadero.administrador.MainActivityAdmin;
import com.example.parqueadero.utils.Config;

public class Imprimir extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Config dataConfig;
    TextView campoTicket ;
    TextView campoPlaca ;
    TextView campoVehiculo ;
    TextView campoTarifa ;
    TextView campoTiempo ;
    TextView campoEntrada ;
    TextView campoSalida ;
    TextView campoTitular ;
    Button salirImprimir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);

        salirImprimir = findViewById(R.id.btn_salirImprimir);
        salirImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Historial.class);
                startActivity(intencion);
            }
        });

        dataConfig = new Config(getApplicationContext());
        sharedPreferences = getSharedPreferences("infoHistorial", Context.MODE_PRIVATE);

        campoTicket = findViewById(R.id.etq_Ticket);
        campoPlaca = findViewById(R.id.etq_Placa);
        campoVehiculo = findViewById(R.id.etq_Vehiculo);
        campoTarifa = findViewById(R.id.etq_Tarifa);
        campoTiempo = findViewById(R.id.etq_Tiempo);
        campoEntrada = findViewById(R.id.etq_Entrada);
        campoSalida = findViewById(R.id.etq_Salida);
        campoTitular = findViewById(R.id.etq_Titular);


    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarInfo();
    }

    public void cargarInfo(){

        campoTicket.setText(sharedPreferences.getString("ticket",""));
        campoPlaca.setText(sharedPreferences.getString("placa",""));
        campoVehiculo.setText(sharedPreferences.getString("vehiculo",""));
        campoTarifa.setText(sharedPreferences.getString("tarifa",""));
        campoTiempo.setText(sharedPreferences.getString("tiempo",""));
        campoEntrada.setText(sharedPreferences.getString("entrada",""));
        campoSalida.setText(sharedPreferences.getString("salida",""));
        campoTitular.setText(sharedPreferences.getString("titular",""));

    }


}