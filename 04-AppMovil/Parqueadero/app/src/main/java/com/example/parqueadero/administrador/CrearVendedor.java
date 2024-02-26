package com.example.parqueadero.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.parqueadero.utils.DetalleHistorial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrearVendedor extends AppCompatActivity {
    Button btnSalirCrearVendedor;
    Button btnCreateVendedor;
    Spinner spinnerAddVendedor;
    EditText campoCedula;
    EditText campoNombre;
    EditText campoApellido;
    EditText campoTelefono;
    EditText campoEmail;
    EditText campoPassword;
    Config dataConfig;
    String cedula;
    String nombre;
    String apellido;
    String telefono;
    String email;
    String password;
    String valorSeleccionado;
    List<String> nombresParqueaderos = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_vendedor);
        btnSalirCrearVendedor = findViewById(R.id.btnSalir);
        dataConfig = new Config(getApplicationContext());
        spinnerAddVendedor = findViewById(R.id.spinnerParqueadero);

        campoCedula = findViewById(R.id.campoCedula);
        campoNombre = findViewById(R.id.campoNombre);
        campoApellido = findViewById(R.id.campoApellido);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoEmail = findViewById(R.id.campoEmail);
        campoPassword = findViewById(R.id.campoPassword);
        btnCreateVendedor = findViewById(R.id.btnCreateVendedor);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresParqueaderos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAddVendedor.setAdapter(adapter);

        spinnerAddVendedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valorSeleccionado = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSalirCrearVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
            }
        });

        btnCreateVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICK AGREGAR V");
                verificarCampos();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiObtenerParking();
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
                            // Agrega el nombre del parqueadero a la lista
                            nombresParqueaderos.add(name);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    ((ArrayAdapter<String>) spinnerAddVendedor.getAdapter()).notifyDataSetChanged();
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

    public void verificarCampos(){
        cedula = campoCedula.getText().toString();
        nombre = campoNombre.getText().toString();
        apellido = campoApellido.getText().toString();
        telefono = campoTelefono.getText().toString();
        email = campoEmail.getText().toString();
        password = campoPassword.getText().toString();
        if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(CrearVendedor.this, "Algun campo esta sin llenar", Toast.LENGTH_SHORT).show();
        } else {
            verificarEstado(cedula);
        }
    }

    public void verificarEstado(String cedula){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/VerificarPersona.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    boolean status = resultado.getBoolean("status");
                    if (status){
                        Toast.makeText(CrearVendedor.this, "Cedula ya Registrada", Toast.LENGTH_SHORT).show();
                    }else {
                        agregarVendedor();
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
                params.put("cedula", cedula);
                return params;
            }
        };
        queue.add(consulta);
    }

    public void agregarVendedor(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/insert.php");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resultado = new JSONObject(response);
                            boolean status = resultado.getBoolean("status");
                            if (status){
                                Toast.makeText(CrearVendedor.this, "Vendedor Creado Exitosamente", Toast.LENGTH_SHORT).show();
                                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                                startActivity(intencion);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error.getMessage());
                Toast.makeText(CrearVendedor.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cedula", cedula);
                params.put("nombre", nombre);
                params.put("apellidos", apellido);
                params.put("celular", telefono);
                params.put("parqueadero", valorSeleccionado);
                params.put("email", email);
                params.put("contrasenia", password);
                return params;
            }
        };
        queue.add(stringRequest);
    }

}