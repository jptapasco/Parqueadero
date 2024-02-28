package com.example.parqueadero.administrador;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.R;
import com.example.parqueadero.utils.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsociarVendedor extends AppCompatActivity {
    Spinner spinnerAsociarParqueadero;
    Config dataConfig;
    EditText campoCedula;
    Button btnCancelarAso;
    EditText campoNombre;
    EditText campoApellido;
    EditText campoTelefono;
    Button btnAsociar;
    SharedPreferences sharedPreferences;
    String valorSeleccionado;
    List<String> nombresParqueaderos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asociar_vendedor);

        btnCancelarAso = findViewById(R.id.btnCancelarAso);
        btnAsociar = findViewById(R.id.btnAsociarVendedor);
        campoCedula = findViewById(R.id.campoCedulaA);
        campoNombre = findViewById(R.id.campoNombreA);
        campoApellido = findViewById(R.id.campoApellidoA);
        campoTelefono = findViewById(R.id.campoTelefonoA);

        sharedPreferences = getSharedPreferences("infoAsignar", Context.MODE_PRIVATE);

        dataConfig = new Config(getApplicationContext());
        spinnerAsociarParqueadero = findViewById(R.id.spinnerAsociarParqueadero);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresParqueaderos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAsociarParqueadero.setAdapter(adapter);

        spinnerAsociarParqueadero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valorSeleccionado = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAsociar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiAsociar();
            }
        });

        btnCancelarAso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), VendedoresSin.class);
                startActivity(intencion);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiObtenerParking();
        reemplazarInfo();
    }

    public void apiObtenerParking(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/Obtener.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    JSONArray datos = respuesta.getJSONArray("registros");
                    for (int i = 0; i < datos.length(); i++) {
                        try {
                            JSONObject dataParking = datos.getJSONObject(i);
                            String name = dataParking.getString("nombre");
                            System.out.println("nombres PK: "+name);
                            nombresParqueaderos.add(name);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    ((ArrayAdapter<String>) spinnerAsociarParqueadero.getAdapter()).notifyDataSetChanged();
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

    public void reemplazarInfo(){
        campoCedula.setText(sharedPreferences.getString("cedula",""));
        campoNombre.setText(sharedPreferences.getString("nombre",""));
        campoApellido.setText(sharedPreferences.getString("apellido",""));
        campoTelefono.setText(sharedPreferences.getString("telefono",""));
    }

    public void apiAsociar(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/AsociarParqueadero.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta: "+respuesta);
                    boolean status = respuesta.getBoolean("status");
                    if (status){
                        mostrarAlerta();
                    } else {
                        mostrarAlertaError();
                    }
                } catch (JSONException e) {
                    System.out.println("todo mal");
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
                params.put("cedula", sharedPreferences.getString("cedula",""));
                params.put("parqueadero", valorSeleccionado);
                return params;
            }
        };
        queue.add(consulta);
    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXITO");
        builder.setMessage("Vendedor asignado exitosamente");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog alerta = builder.create();
        alerta.show();
    }

    private void mostrarAlertaError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se pudo asiciar el Vendedor");
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