package com.example.parqueadero.vendedor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.MainActivity;
import com.example.parqueadero.R;
import com.example.parqueadero.utils.Config;
import com.example.parqueadero.utils.DetalleHistorial;
import com.example.parqueadero.utils.DetalleHistorialAdapter;
import com.example.parqueadero.utils.Tarifa;
import com.example.parqueadero.utils.TarifaAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Tarifas extends AppCompatActivity {

    Config dataConfig;
    List<Tarifa> listaTarifa;
    RecyclerView recyclerView;
    TarifaAdapter adapter;
    EditText campoEditTarifa;
    Spinner spinnerTarifa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifas);

        dataConfig = new Config(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerTarifa);
        spinnerTarifa = findViewById(R.id.spinnerTarifa);
        campoEditTarifa = findViewById(R.id.campoEditTarifa);
        String[] opciones = getResources().getStringArray(R.array.opciones_tarifa);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTarifa.setAdapter(adapter);

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);

        btnParqueaderoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(intencion);
            }
        });
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
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intencion);
            }
        });

        btnTarifa.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiObtenerTarifa();
        clickEnSpinner();
    }

    public void  clickEnSpinner(){
        spinnerTarifa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String opcionSeleccionada = (String) parentView.getItemAtPosition(position);
                System.out.println("opcion elejida:" + opcionSeleccionada);
                apiSetCampoTarifa(opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Ninguna Opcion Seleccionada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void apiSetCampoTarifa(String opcionElejida){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/obtenerTarifa.php?tipo_vehiculo="+opcionElejida);
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    String temporal = respuesta.getString("tarifa");
                    campoEditTarifa.setText(temporal);
                } catch (JSONException e) {
                    System.out.println("El servidor GET responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor GET responde con un error");
                System.out.println(error.getMessage());
            }
        });
        queue.add(solicitud);
    }


    public void apiObtenerTarifa(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/Obtener.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    cargarListaTarifa(respuesta.getJSONArray("registros"));
                } catch (JSONException e) {
                    System.out.println("El servidor GET responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JOA Mani el servidor GET responde con un error");
                System.out.println(error.getMessage());
            }
        });
        queue.add(solicitud);
    }

    public void cargarListaTarifa(JSONArray datos) {
        listaTarifa = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            try {
                JSONObject tarifas = datos.getJSONObject(i);
                listaTarifa.add(new Tarifa(tarifas.getString("id"),tarifas.getString("tipo_vehiculo"),tarifas.getString("Tarifa")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new TarifaAdapter(listaTarifa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}