package com.example.parqueadero.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.R;
import com.example.parqueadero.utils.Config;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrearParqueadero extends AppCompatActivity {

    EditText campo_nit;
    EditText campo_nombre;
    EditText campo_direccion;
    EditText campo_telefono;
    Button salirCrearParqueadero;
    Button btnCreateParking;
    Config dataConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_parqueadero);

        dataConfig = new Config(getApplicationContext());
        salirCrearParqueadero = findViewById(R.id.btn_salirCrearParqueadero);
        btnCreateParking = findViewById(R.id.btnCreateParking);



        salirCrearParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
                startActivity(intencion);
                finish();
            }
        });

        btnCreateParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearParqueadero();
            }
        });
    }

    public void crearParqueadero() {
        campo_nit = findViewById(R.id.campo_nit);
        campo_nombre = findViewById(R.id.campo_nombre);
        campo_nit = findViewById(R.id.campo_direccion);
        campo_telefono = findViewById(R.id.campo_telefono);
        String nit = campo_nit.getText().toString();
        String nombre = campo_nombre.getText().toString();
        String direccion = campo_direccion.getText().toString();
        String telefono = campo_telefono.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/Insert.php");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resultado = new JSONObject(response);
                            System.out.println("Resultado: "+resultado);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: " + error.getMessage());
                Toast.makeText(CrearParqueadero.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nit", nit);
                params.put("nombre", nombre);
                params.put("direccion", direccion);
                params.put("telefono", telefono);
                return params;
            }
        };
        queue.add(stringRequest);
    }

}