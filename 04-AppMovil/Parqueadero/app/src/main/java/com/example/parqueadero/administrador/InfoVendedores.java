package com.example.parqueadero.administrador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.R;
import com.example.parqueadero.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InfoVendedores extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Config dataConfig;
    EditText campoCC;
    EditText campoNombre;
    EditText campoApellido;
    Button btnVolver;
    EditText campoTelefono;
    EditText campoEmail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vendedores);

        dataConfig = new Config(getApplicationContext());
        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE);

        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
            }
        });

        campoCC = findViewById(R.id.campoCedula);
        campoNombre = findViewById(R.id.campoNombre);
        campoApellido = findViewById(R.id.campoApellido);
        campoTelefono = findViewById(R.id.campoTelefono);
        campoEmail = findViewById(R.id.campoEmail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiObtenerEmail();
    }

    public void apiObtenerEmail(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/ConsultarPersona.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    JSONObject persona = resultado.getJSONObject("usuario");
                    String email = persona.getString("email");
                    reemplazarInfo(email);
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
                params.put("cedula", sharedPreferences.getString("cedula",""));
                return params;
            }
        };
        queue.add(consulta);
    }

    public void reemplazarInfo(String email){
        campoCC.setText(sharedPreferences.getString("cedula",""));
        campoNombre.setText(sharedPreferences.getString("nombre",""));
        campoApellido.setText(sharedPreferences.getString("apellido",""));
        campoTelefono.setText(sharedPreferences.getString("telefono",""));
        campoEmail.setText(email);
    }
}