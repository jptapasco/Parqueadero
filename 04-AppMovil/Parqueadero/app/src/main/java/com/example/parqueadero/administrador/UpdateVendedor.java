package com.example.parqueadero.administrador;

import androidx.appcompat.app.AppCompatActivity;

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

public class UpdateVendedor extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Config dataConfig;
    EditText campoCedulaEdit;
    EditText campoNombreEdit;
    EditText campoApellidoEdit;
    EditText campoTelefonoEdit;
    EditText campoEmailEdit;
    EditText campoPasswordEdit;
    Button btnCancelarEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vendedor);

        btnCancelarEdit = findViewById(R.id.btnCancelarEdit);

        sharedPreferences = getSharedPreferences("cc", Context.MODE_PRIVATE);
        dataConfig = new Config(getApplicationContext());

        btnCancelarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiDatos();
    }

    public void apiDatos(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/ConsultarPersona.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    reemplazarDatos(resultado.getJSONObject("persona"),resultado.getJSONObject("usuario"));
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

    public void reemplazarDatos(JSONObject persona, JSONObject usuario) throws JSONException {
        campoCedulaEdit =findViewById(R.id.campoCedulaEdit);
        campoNombreEdit =findViewById(R.id.campoNombreEdit);
        campoApellidoEdit =findViewById(R.id.campoApellidoEdit);
        campoTelefonoEdit =findViewById(R.id.campoTelefonoEdit);
        campoEmailEdit =findViewById(R.id.campoEmailEdit);
        campoPasswordEdit =findViewById(R.id.campoPasswordEdit);

        campoCedulaEdit.setText(persona.getString("cedula"));
        campoNombreEdit.setText(persona.getString("nombre"));
        campoApellidoEdit.setText(persona.getString("apellidos"));
        campoTelefonoEdit.setText(persona.getString("celular"));

        campoEmailEdit.setText(usuario.getString("email"));
        campoPasswordEdit.setText(usuario.getString("contrasenia"));
    }

}