package com.example.parqueadero;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.administrador.MainActivityAdmin;
import com.example.parqueadero.utils.Config;
import com.example.parqueadero.vendedor.MainActivityVendedor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText campo_correo;
    EditText campo_password;
    Config dataConfig;
    String nit;
    String nombre;
    String direccion;
    String telefono;
    String rol;
    String numVendedores;
    String id_asignacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataConfig = new Config(getApplicationContext());
        campo_correo = findViewById(R.id.campo_correo);
        campo_password = findViewById(R.id.campo_password);
        validarSesion();
    }

    public void validarUsuario(View vista) {
        String correo = campo_correo.getText().toString();
        String password = campo_password.getText().toString();

        if (!correo.equals("") && !password.equals("")) {
            apiValidarUsuario(correo, password);
        } else {
            Toast.makeText(getApplicationContext(), "Datos Obligatorios", Toast.LENGTH_LONG).show();
        }
    }

    public void apiValidarUsuario(String correo, String password) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-login/acceso.php");

        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject datos = new JSONObject(response);
                    System.out.println(datos);
                    if (datos.getBoolean("success")) {
                        System.out.println("entro");
                        JSONObject usuario = datos.getJSONObject("user");
                        String status = usuario.getString("estado");
                        rol = usuario.getString("tipo");
                        System.out.println(status);
                        if (status.equalsIgnoreCase("INACTIVO")) {
                            System.out.println("Usuario Inactivo");
                            Toast.makeText(getApplicationContext(), "Usuario INACTIVO", Toast.LENGTH_LONG).show();
                        } else if (rol.equalsIgnoreCase("VENDEDOR")) {
                            String id_usuario = usuario.getString("id");
                            obtenerParking(id_usuario);
                            obtenerAsignacion(id_usuario);
                        } else if (rol.equalsIgnoreCase("ADMIN")) {
                            cambiarActivity(rol);
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
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("correo", correo);
                params.put("contrasena", password);
                return params;
            }
        };

        queue.add(solicitud);
    }

    public void obtenerParking(String id_usuario) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Ticket/obtenerParqueadero.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultado = new JSONObject(response);
                    JSONArray parqueadero = resultado.getJSONArray("parqueaderos");
                    for (int i = 0; i < parqueadero.length(); i++) {
                        JSONObject parqueaderoObj = parqueadero.getJSONObject(i);

                        nit = parqueaderoObj.getString("nit");
                        nombre = parqueaderoObj.getString("nombre");
                        direccion = parqueaderoObj.getString("direccion");
                        telefono = parqueaderoObj.getString("telefono");
                        numVendedores = parqueaderoObj.getString("num_vendedores");
                    }
                    cambiarActivity(rol);
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
                params.put("id_usuario", id_usuario);
                return params;
            }
        };
        queue.add(consulta);
    }

    public void obtenerAsignacion(String id_usuario) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/obtenerIdAsignacion.php");
        StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    id_asignacion = respuesta.getString("id_asignacion");
                    cambiarActivity(rol);
                    System.out.println("ID Asignacion: " + id_asignacion);
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
                params.put("id_usuario", id_usuario);
                return params;
            }
        };
        queue.add(consulta);
    }

    public void cambiarActivity(String rol) {
        if (rol.equalsIgnoreCase("ADMIN")) {
            System.out.println("INICIO SESION COMO ADMIN");
            Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
            startActivity(intencion);
            finish();
        } else if (rol.equalsIgnoreCase("VENDEDOR")){
            System.out.println("INICIO SESION COMO VENDEDOR");
            Intent intencion = new Intent(getApplicationContext(), MainActivityVendedor.class);
            intencion.putExtra("nit", nit);
            intencion.putExtra("nombre", nombre);
            intencion.putExtra("direccion", direccion);
            intencion.putExtra("telefono", telefono);
            intencion.putExtra("numVendedores", numVendedores);
            intencion.putExtra("id_asignacion", id_asignacion);
            startActivity(intencion);
            finish();
        }
    }

    public void validarSesion() {
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