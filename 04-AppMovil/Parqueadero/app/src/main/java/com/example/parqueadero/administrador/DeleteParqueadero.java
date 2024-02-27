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

public class DeleteParqueadero extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String nit;
    Button btnCancelarDelete;
    Button btnEliminarPk;
    EditText campoNitDelete;
    EditText campoNombreDelete;
    EditText campoDireccionDelete;
    EditText campoTelefonoDelete;
    Config dataConfig;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_parqueadero);
        btnCancelarDelete = findViewById(R.id.btnCancelarDelete);
        btnEliminarPk = findViewById(R.id.btnEliminarPk);

        btnEliminarPk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiDeletePk();
            }
        });

        btnCancelarDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
                startActivity(intencion);
            }
        });

        dataConfig = new Config(getApplicationContext());
        sharedPreferences = getSharedPreferences("datos_delete", Context.MODE_PRIVATE);

        campoNitDelete = findViewById(R.id.campoNitDelete);
        campoNombreDelete = findViewById(R.id.campoNombreDelete);
        campoDireccionDelete = findViewById(R.id.campoDireccionDelete);
        campoTelefonoDelete = findViewById(R.id.campoTelefonoDelete);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reemplazarInfo();
    }
    public void reemplazarInfo(){
        campoNitDelete.setText(sharedPreferences.getString("nit",""));
        campoNombreDelete.setText(sharedPreferences.getString("nombre",""));
        campoDireccionDelete.setText(sharedPreferences.getString("direccion",""));
        campoTelefonoDelete.setText(sharedPreferences.getString("telefono",""));
    }

    public void apiDeletePk(){
        String nit = sharedPreferences.getString("nit","");

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/Delete.php");
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
                        mostrarAlertaError();
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
                return params;
            }
        };
        queue.add(consulta);
    }


    private void mostrarAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXITO");
        builder.setMessage("Parqueadero ELIMINADO exito");

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

    private void mostrarAlertaError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("Error al Eliminar el parqueadero debe estar asociado a un vendedor");

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