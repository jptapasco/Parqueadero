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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void reemplazarDatos(){
        namePk.setText(nombre);
        etqDireccionIn.setText(direccion);
        etqtelefonoIn.setText(telefono);
        etqTicketIn.setText(ticket);
        //etqEstanciaIn.setText();
        etqPlacaIn.setText(placaBusqueda);
        //etqTipoVehiculoIn.setText();
        etqIngresoIn.setText(createEntrada);
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