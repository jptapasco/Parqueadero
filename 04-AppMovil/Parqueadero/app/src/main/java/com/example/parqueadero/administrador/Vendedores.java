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
import com.example.parqueadero.utils.Persona;
import com.example.parqueadero.utils.PersonaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vendedores extends AppCompatActivity {
    Button btnParqueadero;
    Button btnVendedor;
    Button btnSalir;
    EditText campo_buscar_documento;
    Button btnBuscarVendedor;
    Button btnVendedoresAsignados;
    FloatingActionButton btn_crear_vendedor;
    Button getBtnVendedoresSinAsignacion;
    Config dataConfig;
    List<Persona> listaPersona;
    RecyclerView recyclerView;
    PersonaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedores);

        btnParqueadero = findViewById(R.id.btn_parqueadero);
        btnVendedor = findViewById(R.id.btn_vendedores);
        btnSalir = findViewById(R.id.btn_salir);
        btnVendedoresAsignados = findViewById(R.id.btnVendedorAsignado);
        getBtnVendedoresSinAsignacion = findViewById(R.id.btnVendedorSinAsignar);
        btnVendedor.setEnabled(false);
        btn_crear_vendedor = findViewById(R.id.btn_crear_vendedor);
        btnBuscarVendedor= findViewById(R.id.btnBuscarVendedor);
        campo_buscar_documento = findViewById(R.id.campo_buscar_documento);

        btnBuscarVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String documentoBusqueda = campo_buscar_documento.getText().toString();

                if (!documentoBusqueda.equals("") ){
                    buscadorVendedor(documentoBusqueda);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa el Documento", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_crear_vendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), CrearVendedor.class);
                startActivity(intencion);
            }
        });
        btnParqueadero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityAdmin.class);
                startActivity(intencion);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intencion);
            }
        });

        btnVendedoresAsignados.setEnabled(false);

        getBtnVendedoresSinAsignacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), VendedoresSin.class);
                startActivity(intencion);
            }
        });

        dataConfig = new Config(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerListaVendedores);
        listaPersona = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vendedoresConAsignacion();
    }

    public void vendedoresConAsignacion() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/ObtenerPersonas.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta API Personas Con Asignacion: " + respuesta);
                    cargarListaPersonasAsignadas(respuesta.getJSONArray("registros"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
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

    public void cargarListaPersonasAsignadas(JSONArray datos) {
        List<Persona> listaPersonaAsignada = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            try {
                JSONObject vendedor = datos.getJSONObject(i);
                listaPersonaAsignada.add(new Persona(vendedor.getString("cedula"), vendedor.getString("nombre"), vendedor.getString("apellidos"), vendedor.getString("celular")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new PersonaAdapter(listaPersonaAsignada);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void buscadorVendedor(String cedula){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/VerificarPersona.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject datos = new JSONObject(response);
                    boolean status = datos.getBoolean("status");
                    if (status){
                        cargarListaPersonasAsignadas(datos.getJSONArray("registros"));
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
                params.put("cedula", cedula);
                return params;
            }
        };
        queue.add(solicitud);
    }
}