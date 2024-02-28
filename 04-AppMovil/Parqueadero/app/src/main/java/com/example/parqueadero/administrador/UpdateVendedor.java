package com.example.parqueadero.administrador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
    Button btnEditarVendedor;
    EditText campoNombreEdit;
    EditText campoApellidoEdit;
    EditText campoTelefonoEdit;
    EditText campoEmailEdit;
    EditText campoPasswordEdit;
    Button btnCancelarEdit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vendedor);

        btnCancelarEdit = findViewById(R.id.btnCancelarEdit);
        btnEditarVendedor = findViewById(R.id.btnEditarVendedor);

        sharedPreferences = getSharedPreferences("cc", Context.MODE_PRIVATE);
        dataConfig = new Config(getApplicationContext());

        btnCancelarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
            }
        });
        btnEditarVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturarDatos();
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

    public void capturarDatos(){
        String cedula = campoCedulaEdit.getText().toString();
        String nombre = campoNombreEdit.getText().toString();
        String apellido = campoApellidoEdit.getText().toString();
        String telefono = campoTelefonoEdit.getText().toString();
        String email = campoEmailEdit.getText().toString();
        String pass = campoPasswordEdit.getText().toString();

        if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || pass.isEmpty()){
            mostrarAlertaError("Datos incompletos");
        } else {
            apiUpdateVendedor(cedula,nombre,apellido,telefono,email,pass);
        }

    }

    public void apiUpdateVendedor(String cedula, String nombre, String apellido, String telefono, String email, String pass){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/Update.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    System.out.println("respuesta: "+resultado);
                    boolean status = resultado.getBoolean("status");
                    if (status){
                        mostrarAlerta();
                    } else{
                        mostrarAlertaError("Hubo un error al actualizar los datos intenta de nuevo");
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
                params.put("nombre", nombre);
                params.put("apellidos", apellido);
                params.put("celular", telefono);
                params.put("email", email);
                params.put("contrasenia", pass);
                return params;
            }
        };
        queue.add(consulta);
    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXITO");
        builder.setMessage("Vendedor actualizado con exito");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
                dialog.dismiss();
            }
        });

        AlertDialog alerta = builder.create();
        alerta.show();
    }

    private void mostrarAlertaError(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage(mensaje);

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