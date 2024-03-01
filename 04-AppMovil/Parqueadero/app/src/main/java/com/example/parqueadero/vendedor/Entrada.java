package com.example.parqueadero.vendedor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.parqueadero.administrador.MainActivityAdmin;
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
    String opcionSeleccionada;
    EditText campo_placa;
    EditText campo_titular;
    String id_tarifa;
    String placa;
    String titular;
    Button btnCrearEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);
        btnCrearEntrada = findViewById(R.id.btnCrearEntrada);

        campo_placa = findViewById(R.id.campo_placa_E);
        campo_titular = findViewById(R.id.campo_titular_E);

        dataConfig = new Config(getApplicationContext());
        spinnerTarifa = findViewById(R.id.spinnerTarifaEntrada);
        sharedPreferences = getSharedPreferences("mis_datos", Context.MODE_PRIVATE);
        id_asignacion = sharedPreferences.getString("id_asignacion", "");
        System.out.println(id_asignacion);
        System.out.println(id_asignacion);
        System.out.println("si dio");

        String[] opciones = getResources().getStringArray(R.array.opciones_tarifa);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

        btnCrearEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        clickEnSpinner();
    }

    public void  clickEnSpinner(){
        spinnerTarifa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                opcionSeleccionada = (String) parentView.getItemAtPosition(position);
                System.out.println("opcion elejida: " + opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Ninguna Opcion Seleccionada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validarDatos(){
        placa = String.valueOf(campo_placa.getText());
        titular = String.valueOf(campo_titular.getText());

        if (placa.isEmpty() || titular.isEmpty()){
            Toast.makeText(getApplicationContext(), "Digita los campos por favor", Toast.LENGTH_LONG).show();
        } else {
            verificarPlaca();
        }
    }

    public void verificarPlaca(){
        System.out.println("Entro a VERIFICAR PLACA");
        System.out.println();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/VerificarPlaca.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("respuesta Verificar Placa: "+respuesta);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        obtenerTarifa();
                    }else{
                        apiInsertarRegistro();
                    }
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JOA Mani el servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("placa", placa);
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void apiInsertarRegistro(){
        System.out.println("Entro a INSERTAR REGISTRO");
        System.out.println();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Ticket/insertRegistro.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        obtenerTarifa();
                    }else{
                        mostrarAlertaError();
                    }
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JOA Mani el servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("placa", placa);
                params.put("responsable", titular);
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void obtenerTarifa(){
        System.out.println("Entro a OBTENER TARIFA");
        System.out.println();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/ObtenerIdTarifa.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("LO QUE ME LLEGO PARA TARIFAS ID: "+respuesta);
                    boolean status = respuesta.getBoolean("success");
                    if (status){
                        id_tarifa = respuesta.getString("id_tarifa");
                        insertarTicket();
                    }else{
                        mostrarAlertaError();
                    }
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JOA Mani el servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tipo_vehiculo", opcionSeleccionada);
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void insertarTicket(){
        System.out.println("Entro a INSERTAR TICKET");
        System.out.println();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Ticket/insertTicket.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("respuesta Insertar TICKET: "+respuesta);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        mostrarAlerta();
                    }else{
                        mostrarAlertaError();
                    }
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JOA Mani el servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("placa", placa);
                params.put("id_asignacion", id_asignacion);
                params.put("id_tarifa", id_tarifa);
                return params;
            }
        };
        queue.add(solicitud);
    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXITO");
        builder.setMessage("Registro exitoso");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(intencion);
                dialog.dismiss();
            }
        });

        AlertDialog alerta = builder.create();
        alerta.show();
    }

    private void mostrarAlertaError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se pudo hacer el ingreso");

        builder.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alerta = builder.create();
        alerta.show();
    }



}