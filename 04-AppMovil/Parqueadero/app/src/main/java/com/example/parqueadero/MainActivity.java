package com.example.parqueadero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.administrador.MainActivityAdmin;
import org.json.JSONArray;
import com.example.parqueadero.utils.Config;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText campo_correo;
    EditText campo_password;
    Config dataConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataConfig = new Config(getApplicationContext());
        campo_correo = findViewById(R.id.campo_correo);
        campo_password = findViewById(R.id.campo_password);
        validarSesion();
    }

    public void validarUsuario(View vista){
        String correo = campo_correo.getText().toString();
        String password = campo_password.getText().toString();

        if ( !correo.equals("") && !password.equals("") ){
            apiValidarUsuario(correo, password);
        }else{
            Toast.makeText( getApplicationContext(), "Datos Obligatorios", Toast.LENGTH_LONG ).show();
        }
    }

    public void apiValidarUsuario(String correo, String password){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-login/acceso.php");

        StringRequest solicitud =  new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject datos = new JSONObject(response);
                    System.out.println(datos);
                    if(datos.getBoolean("success")){
                        System.out.println("entro");
                        JSONObject usuario = datos.getJSONObject("user");
                        String status = usuario.getString("estado");
                        String rol = usuario.getString("tipo");
                        System.out.println(status);
                        if (status.equalsIgnoreCase("INACTIVO")){
                            System.out.println("Usuario Inactivo");
                            Toast.makeText( getApplicationContext(), "Usuario INACTIVO", Toast.LENGTH_LONG ).show();
                        } else if (rol.equalsIgnoreCase("VENDEDOR")) {
                            String id_usuario  = usuario.getString("id");
                            obtenerParking(id_usuario);
                        }

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
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("correo", correo);
                params.put("contrasena", password);
                return params;
            }
        };

        queue.add(solicitud);
    }

    public void obtenerParking(String id_usuario){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Ticket/obtenerParqueadero.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    JSONArray parqueadero = resultado.getJSONArray("parqueaderos");
                    //JSONObject parq = parqueadero.getJSONObject("");
                    //System.out.println("resultado: "+parq);
                    //String nombre = parq.getString("nombre");
                    //String nit = parq.getString(Integer.parseInt("nit"));
                    //String direccion = parq.getString(Integer.parseInt("direccion"));
                    //String telefono= parq.getString(Integer.parseInt("telefono"));
                    //String user = parq.getString(Integer.parseInt("num_vendedores"));
                    System.out.println(parqueadero);
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
        }){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_usuario", id_usuario);
                return params;
            }
        };
        queue.add(consulta);
    }
    public void cambiarActivity(String id_usuario, String nombres){
        SharedPreferences archivo = getSharedPreferences("Parqueadero", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = archivo.edit();
        editor.putString("id_usuario", id_usuario);
        editor.putString("nombres", nombres);
        editor.apply();

        Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
        startActivity(intencion);
        finish();
    }

    public void validarSesion(){
        SharedPreferences sharedPreferences = getSharedPreferences("Parqueadero", Context.MODE_PRIVATE);

        String idUsuario = sharedPreferences.getString("id_usuario", null);
        String nombres = sharedPreferences.getString("nombres", null);

        if (idUsuario != null && nombres != null) {
            Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
            startActivity(intencion);
            finish();
        }
    }

}