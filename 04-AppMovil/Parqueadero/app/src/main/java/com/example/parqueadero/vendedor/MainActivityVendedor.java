package com.example.parqueadero.vendedor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
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
import com.example.parqueadero.utils.DetalleHistorial;
import com.example.parqueadero.utils.DetalleHistorialAdapter;
import com.example.parqueadero.utils.VehiculosEnParqueaderoAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityVendedor extends AppCompatActivity {

    TextView etqNombre;
    TextView etqNit;
    TextView etqDireccion;
    TextView etqTelefono;
    TextView etqNvendedores;
    EditText campo_buscar_auto;
    Button btnBuscarAuto;
    SharedPreferences sharedPreferences;
    String id_asignacion;
    RecyclerView recyclerView;
    Config dataConfig;
    VehiculosEnParqueaderoAdapter adapter;
    List<DetalleHistorial> listaAutosEnPk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendedor);

        etqNit = findViewById(R.id.etqNit);
        etqNombre = findViewById(R.id.etqNombre);
        etqDireccion = findViewById(R.id.etqDireccion);
        etqTelefono = findViewById(R.id.etqTelefono);
        etqNvendedores = findViewById(R.id.etqVendedores);
        campo_buscar_auto = findViewById(R.id.campo_buscar_auto);
        btnBuscarAuto = findViewById(R.id.btnBuscarAuto);

        recyclerView = findViewById(R.id.recyclerListaVehiculos);
        dataConfig = new Config(getApplicationContext());
        sharedPreferences = getSharedPreferences("mis_datos", Context.MODE_PRIVATE);
        id_asignacion = sharedPreferences.getString("id_asignacion", "");

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);

        btnBuscarAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placaBusqueda = campo_buscar_auto.getText().toString();

                if (!placaBusqueda.equals("") ){
                    buscadorAuto(placaBusqueda);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa la placa", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnParqueaderoV.setEnabled(false);
        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("mis_datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id_asignacion", id_asignacion);
                editor.apply();
                Intent intencion = new Intent(getApplicationContext(), Entrada.class);
                startActivity(intencion);
            }
        });
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Historial.class);
                startActivity(intencion);
            }
        });
        btnTarifa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Tarifas.class);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiListaVehiculos();
        reemplazarInfo();
    }

    public void reemplazarInfo(){
        String nit = sharedPreferences.getString("nit", "");
        String nombre = sharedPreferences.getString("nombre", "");
        String direccion = sharedPreferences.getString("direccion", "");
        String telefono = sharedPreferences.getString("telefono", "");
        String numVendedores = sharedPreferences.getString("numVendedores", "");

        etqNit.setText(nit);
        etqNombre.setText(nombre);
        etqDireccion.setText(direccion);
        etqTelefono.setText(telefono);
        etqNvendedores.setText(numVendedores);
    }

    public void apiListaVehiculos(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-voce/obtenerParqueadero.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta api Vehiculos EN: " + respuesta);
                    cargarListaVehiculos(respuesta.getJSONArray("registros"));
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

    //FUNCIÓN CALCULAR TIEMPO
    private String calcularTiempo(String ingreso) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date fechaIngreso = format.parse(ingreso);
            Date fechaActual = new Date();
            long diferencia = fechaActual.getTime() - fechaIngreso.getTime();
            long horas = diferencia / (60 * 60 * 1000);
            long minutos = (diferencia % (60 * 60 * 1000)) / (60 * 1000);
            return horas + "h " + minutos + "m";
        } catch (Exception e) {
            System.err.println("Error al calcular tiempo: " + e.getMessage());
            return "";
        }
    }

    public void cargarListaVehiculos(JSONArray datos) {
        listaAutosEnPk = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            try {
                JSONObject historial = datos.getJSONObject(i);
                String entrada = historial.getString("create_entrada");
                String tiempo = calcularTiempo(entrada);
                listaAutosEnPk.add(new DetalleHistorial(historial.getString("id"),historial.getString("tipo_vehiculo"),historial.getString("placa"), historial.getString("responsable"), historial.getString("Tarifa"), entrada, tiempo ));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new VehiculosEnParqueaderoAdapter(listaAutosEnPk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void buscadorAuto(String placa){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-voce/buscarVehiculo.php?busqueda="+placa);
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject datos = new JSONObject(response);
                    JSONArray resultado = datos.getJSONArray("registros");
                    if (resultado.length() > 0){
                        cargarListaVehiculos(resultado);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Se encontraron Resultados", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Joa mani el servidor GET responde con un error:");
                System.out.println(error.getMessage());
            }
        });
        queue.add(solicitud);
    }
}