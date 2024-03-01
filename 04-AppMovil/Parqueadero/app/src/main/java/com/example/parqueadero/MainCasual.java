package com.example.parqueadero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class MainCasual extends AppCompatActivity {

    Button btnBuscarInvitado;
    Config dataConfig;
    EditText campoBuscarPlaca;
    TextView namePk;
    TextView etqDireccionIn;
    TextView etqtelefonoIn;
    TextView etqTicketIn;
    TextView etqEstanciaIn;
    TextView etqPlacaIn;
    TextView etqTipoVehiculoIn;
    TextView etqIngresoIn;
    TextView etqValorTarifaIn;
    String ticket;
    String idAsignacion;
    String idTarifa;
    String createEntrada;
    String tipoVehiculo;
    String valorTarifa;
    String idUsuario;
    String idPk;
    String nombre;
    String direccion;
    String telefono;
    String tiempo;
    String placaBusqueda;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_casual);

        dataConfig = new Config(getApplicationContext());

        btnBuscarInvitado = findViewById(R.id.btnBuscarInvitado);
        campoBuscarPlaca = findViewById(R.id.campo_buscar_placa);

        namePk = findViewById(R.id.namePkIn);
        etqDireccionIn = findViewById(R.id.etqDireccionIn);
        etqtelefonoIn = findViewById(R.id.etqtelefonoIn);
        etqTicketIn = findViewById(R.id.etqTicketIn);
        etqEstanciaIn = findViewById(R.id.etqEstanciaIn);
        etqPlacaIn = findViewById(R.id.etqPlacaIn);
        etqTipoVehiculoIn = findViewById(R.id.etqTipoVehiculoIn);
        etqIngresoIn = findViewById(R.id.etqIngresoIn);
        etqValorTarifaIn = findViewById(R.id.etqValorTarifaIn);

        btnBuscarInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placaBusqueda = campoBuscarPlaca.getText().toString();

                if (!placaBusqueda.equals("") ){
                    buscarPlaca();
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa la placa", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void btnIniciar(View view){
        Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intencion);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void buscarPlaca(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/buscarVehiculoInvitado.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        JSONObject resultado = respuesta.getJSONObject("resultado");
                        ticket = resultado.getString("id");
                        idAsignacion = resultado.getString("id_asignacion");
                        idTarifa = resultado.getString("id_tarifa");
                        createEntrada = resultado.getString("create_entrada");
                        tiempo = calcularTiempo(createEntrada);
                        obtenerTarifa();
                    }else {
                        mostrarAlertaError();
                    }
                    System.out.println("Respuesta buscar placa: "+respuesta);
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
                params.put("placa", campoBuscarPlaca.getText().toString());
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void obtenerTarifa(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/obtenerTarifa.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta Obtener Tarifa: "+respuesta);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        JSONObject resultado = respuesta.getJSONObject("resultado");
                        tipoVehiculo = resultado.getString("tipo_vehiculo");
                        valorTarifa = resultado.getString("Tarifa");
                        obtenerAsignacion();
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
                params.put("id_tarifa", idTarifa);
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void obtenerAsignacion(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/obtenerAsignacion.php");
        System.out.println("idA: "+idAsignacion);
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta Obtener Asignacion: "+respuesta);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        JSONObject resultado = respuesta.getJSONObject("resultado");
                        idUsuario = resultado.getString("id_parqueadero");
                        idPk = resultado.getString("id_usuario");
                        obtenerParking();
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
                params.put("id_asignacion", idAsignacion);
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void obtenerParking() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Ticket/obtenerParqueadero.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    JSONArray parqueadero = resultado.getJSONArray("parqueaderos");
                    System.out.println("Datos PK: "+resultado);
                    for (int i = 0; i < parqueadero.length(); i++) {
                        JSONObject parqueaderoObj = parqueadero.getJSONObject(i);

                        nombre = parqueaderoObj.getString("nombre");
                        direccion = parqueaderoObj.getString("direccion");
                        telefono = parqueaderoObj.getString("telefono");
                        reemplazarDatos();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Joa mani el servidor POST responde con un error:");
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_usuario", idUsuario);
                return params;
            }
        };
        queue.add(consulta);
    }

    private String calcularTiempo(String ingreso) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Establecer la zona horaria a America/Bogota
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));

        try {
            Date fechaIngreso = format.parse(ingreso);
            Date fechaActual = new Date();
            System.out.println("fecha Ingreso: " + fechaIngreso);
            System.out.println("fecha actual: "+fechaActual);
            long diferencia = fechaActual.getTime() - fechaIngreso.getTime();
            long horas = diferencia / (60 * 60 * 1000);
            long minutos = (diferencia % (60 * 60 * 1000)) / (60 * 1000);
            System.out.println(horas + "h " + minutos + "m");
            return horas + "h " + minutos + "m";
        } catch (Exception e) {
            System.err.println("Error al calcular tiempo: " + e.getMessage());
            return "";
        }
    }

    public void reemplazarDatos(){

        String tarifa = valorTarifa;
        String tiempoC = tiempo;
        double tarifaDouble = Double.parseDouble(tarifa);

        double tarifaPorHora = tarifaDouble;
        String tiempoT = tiempoC;

        int horas = 0;
        int minutos = 0;

        if (tiempoT.contains("h")) {
            String[] partesTiempo = tiempoT.split("h");
            horas = Integer.parseInt(partesTiempo[0].trim());

            if (partesTiempo[1].contains("m")) {
                minutos = Integer.parseInt(partesTiempo[1].replace("m", "").trim());
            }
        } else if (tiempoT.contains("m")) {
            minutos = Integer.parseInt(tiempoT.replace("m", "").trim());
        }

        double tarifaTotal = (horas * tarifaPorHora) + ((minutos > 0) ? tarifaPorHora : 0);


        namePk.setText(nombre);
        etqDireccionIn.setText(direccion);
        etqtelefonoIn.setText(telefono);
        etqTicketIn.setText(ticket);
        etqEstanciaIn.setText(tiempo);
        etqPlacaIn.setText(placaBusqueda);
        etqTipoVehiculoIn.setText(tipoVehiculo);
        etqIngresoIn.setText(createEntrada);
        etqValorTarifaIn.setText(String.valueOf(tarifaTotal));
    }


    private void mostrarAlertaError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SIN INFORMACION");
        builder.setMessage("No se encontraron resultados");

        builder.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                campoBuscarPlaca.setText("");
            }
        });

        AlertDialog alerta = builder.create();
        alerta.show();
    }


}