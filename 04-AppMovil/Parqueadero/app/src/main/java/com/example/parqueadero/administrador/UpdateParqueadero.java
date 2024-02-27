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
import android.widget.Toast;

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

public class UpdateParqueadero extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText campoNit;
    EditText campoNombre;
    EditText campoDireccion;
    EditText campoTelefono;
    Button btnCancelar;
    Config dataConfig;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parqueadero);
        sharedPreferences = getSharedPreferences("datos_edit", Context.MODE_PRIVATE);

        campoNit = findViewById(R.id.campoNitEdit);
        campoNombre = findViewById(R.id.campoNombreEdit);
        campoDireccion = findViewById(R.id.campoDireccionEdit);
        campoTelefono = findViewById(R.id.campoTelefonoEdit);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
                startActivity(intencion);
            }
        });
        dataConfig = new Config(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        reemplazarInfo();
    }
    public void reemplazarInfo(){
        campoNit.setText(sharedPreferences.getString("nit",""));
        campoNombre.setText(sharedPreferences.getString("nombre",""));
        campoDireccion.setText(sharedPreferences.getString("direccion",""));
        campoTelefono.setText(sharedPreferences.getString("telefono",""));
    }

    public void btnEditarPk(View view){
        String nit = campoNit.getText().toString();
        String nombre = campoNombre.getText().toString();
        String direccion = campoDireccion.getText().toString();
        String telefono = campoTelefono.getText().toString();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()){
            mostrarAlertaError("Faltan campos por llenar");
        } else {
            apiEditarPk(nit,nombre,direccion,telefono);
        }
    }

    public void apiEditarPk(String nit, String nombre, String direccion, String telefono){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/Update.php");
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
                params.put("nit", nit);
                params.put("nombre", nombre);
                params.put("direccion", direccion);
                params.put("telefono", telefono);
                return params;
            }
        };
        queue.add(consulta);
    }

    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXITO");
        builder.setMessage("Parqueadero actualizado con exito");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
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