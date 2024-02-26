package com.example.parqueadero.vendedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.parqueadero.administrador.CrearParqueadero;
import com.example.parqueadero.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Entrada extends AppCompatActivity {
    Config dataConfig;
    Spinner spinnerTarifa;
    SharedPreferences sharedPreferences;
    String id_asignacion;
    EditText campo_placa;
    EditText campo_titular;
    String idTarifa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);

        dataConfig = new Config(getApplicationContext());
        spinnerTarifa = findViewById(R.id.spinnerTarifaEntrada);
        sharedPreferences = getSharedPreferences("mis_datos", Context.MODE_PRIVATE);
        id_asignacion = sharedPreferences.getString("id_asignacion", "");
        System.out.println(id_asignacion);
        System.out.println(id_asignacion);
        System.out.println("si dio");

        String[] opciones = getResources().getStringArray(R.array.opciones_tarifa);

        // Crear un adaptador para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        // Especificar el diseño del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Configurar el adaptador en el spinner
        spinnerTarifa.setAdapter(adapter);
        btnParqueaderoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(intencion);
            }
        });
        btnEntrada.setEnabled(false);
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

    @Override
    protected void onResume() {
        super.onResume();
        apiObtenerTarifa();
        clickEnSpinner();
    }

    public void apiObtenerTarifa(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/Obtener.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta api Tarifa " + respuesta);
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

    public void  clickEnSpinner(){
        spinnerTarifa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String opcionSeleccionada = (String) parentView.getItemAtPosition(position);
                System.out.println("opcion elejida: " + opcionSeleccionada);
                idTarifa(opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Ninguna Opcion Seleccionada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void idTarifa(String opcionSeleccionada) {
        String opc = opcionSeleccionada;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/ObtenerIdTarifa.php");

        // Crear una solicitud POST
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    String mensaje = respuesta.getString("message");
                    idTarifa = respuesta.getString("id_tarifa");
                    System.out.println(mensaje);
                    System.out.println(idTarifa);
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            // Sobrescribir este método para pasar los parámetros en el cuerpo de la solicitud
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("tipo_vehiculo", opc);
                return params;
            }
        };

        // Agregar la solicitud a la cola de solicitudes
        queue.add(solicitud);
    }


    public void crearEntrada(View vista) {
        System.out.println("si entro ");
        campo_placa = findViewById(R.id.campo_placa);


        String placa = campo_placa.getText().toString();
        String tarifa = idTarifa.toString();
        String asignacion = id_asignacion.toString();

        System.out.println(placa);
        System.out.println(tarifa);
        System.out.println(asignacion);


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Ticket/insertTicket.php");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resultado = new JSONObject(response);
                            Toast.makeText(Entrada.this, "Ticket creado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error.getMessage());
                Toast.makeText(Entrada.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("placa", placa);
                params.put("id_asignacion", asignacion);
                params.put("id_tarifa", tarifa);
                return params;
            }
        };
        queue.add(stringRequest);
    }



}