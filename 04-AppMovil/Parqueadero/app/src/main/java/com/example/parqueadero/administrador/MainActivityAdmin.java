package com.example.parqueadero.administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.MainActivity;
import com.example.parqueadero.R;
import com.example.parqueadero.utils.Config;
import com.example.parqueadero.utils.Parqueadero;
import com.example.parqueadero.utils.ParqueaderoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityAdmin extends AppCompatActivity {
    Button btnParqueadero;
    Button btnVendedor;
    Button btnBuscarParqueadero;
    Button btnSalir;
    Config dataConfig;
    Button btnCrearParqueadero;
    EditText campo_buscar_parqueadero;
    List<Parqueadero> listaParqueadero;
    RecyclerView recyclerView;
    ParqueaderoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        btnParqueadero = findViewById(R.id.btn_parqueadero);
        btnVendedor = findViewById(R.id.btn_vendedores);
        btnSalir = findViewById(R.id.btn_salir);
        btnCrearParqueadero = findViewById(R.id.btnCrearParqueadero);
        recyclerView = findViewById(R.id.recyclerListaParqueadero);
        campo_buscar_parqueadero = findViewById(R.id.campo_buscar_parqueadero);
        btnBuscarParqueadero = findViewById(R.id.btnBuscarParqueadero);

        dataConfig = new Config(getApplicationContext());
        listaParqueadero = new ArrayList<>();
        btnParqueadero.setEnabled(false);


        btnBuscarParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nitBusqueda = campo_buscar_parqueadero.getText().toString();

                if (!nitBusqueda.equals("") ){
                    buscadorParqueadero(nitBusqueda);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa el nit", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Vendedores.class);
                startActivity(intencion);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intencion);
                finishAffinity();
            }
        });

        btnCrearParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), CrearParqueadero.class);
                startActivity(intencion);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        obtenerParqueaderos();
    }

    public void obtenerParqueaderos() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/Obtener.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta api parqueadero: " + respuesta);
                    cargarListaParqueadero(respuesta.getJSONArray("registros"));
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

    public void cargarListaParqueadero(JSONArray datos) {
        listaParqueadero = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            try {
                JSONObject parqueadero = datos.getJSONObject(i);
                listaParqueadero.add(new Parqueadero(parqueadero.getString("nit"), parqueadero.getString("nombre"), parqueadero.getString("direccion"),parqueadero.getString("telefono")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new ParqueaderoAdapter(listaParqueadero,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void buscadorParqueadero(String nit){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-parqueadero/VerificarParqueadero.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject datos = new JSONObject(response);
                    boolean status = datos.getBoolean("status");
                    if (status){
                        cargarListaParqueadero(datos.getJSONArray("registros"));
                    }else {
                        Toast.makeText(getApplicationContext(), "No Se encontraron Resultados", Toast.LENGTH_LONG).show();
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
        queue.add(solicitud);
    }
}