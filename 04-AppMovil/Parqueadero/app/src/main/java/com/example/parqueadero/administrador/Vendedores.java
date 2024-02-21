package com.example.parqueadero.administrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Vendedores extends AppCompatActivity {
    Button btnParqueadero;
    Button btnVendedor;
    Button btnSalir;
    Button btnVendedoresAsignados;
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

        btnVendedoresAsignados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendedoresConAsignacion();
            }
        });

        getBtnVendedoresSinAsignacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendedoresSinAsignacion();
            }
        });

        dataConfig = new Config(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerListaVendedores);
        listaPersona = new ArrayList<>();
    }


    public void vendedoresConAsignacion(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/ObtenerPersonas.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta API Personas Con Asignacion: "+respuesta);
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


    public void vendedoresSinAsignacion(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-Personas/ObtenerPersonasSinAsignar.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta API Personas SIN Asignacion: "+respuesta);
                    cargarListaPersonasSinAsignacion(respuesta.getJSONArray("registros"));
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

    public void cargarListaPersonasAsignadas(JSONArray datos){
        List<Persona> listaPersonaAsignada = new ArrayList<>();
        for (int i = 0 ; i < datos.length() ; i++ ){
            try {
                JSONObject vendedor = datos.getJSONObject(i);
                listaPersonaAsignada.add(new Persona(vendedor.getString("cedula"),vendedor.getString("nombre"), vendedor.getString("apellidos")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new PersonaAdapter(listaPersonaAsignada);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void cargarListaPersonasSinAsignacion(JSONArray datos){
        List<Persona> listaPersonaSinAsignacion = new ArrayList<>();
        for (int i = 0 ; i < datos.length() ; i++ ){
            try {
                JSONObject vendedor = datos.getJSONObject(i);
                listaPersonaSinAsignacion.add(new Persona(vendedor.getString("cedula"),vendedor.getString("nombre"), vendedor.getString("apellidos")));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new PersonaAdapter(listaPersonaSinAsignacion);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }


}