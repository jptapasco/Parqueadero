package com.example.parqueadero.vendedor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Historial extends AppCompatActivity {
    Config dataConfig;
    List<DetalleHistorial> listaDetalleHistorial;
    SharedPreferences sharedPreferences;
    EditText campo_buscar_placaH;
    Button btnBuscar;
    String id_asignacion;
    TextView textFecha;
    RecyclerView recyclerView;
    DetalleHistorialAdapter adapter;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);

        sharedPreferences = getSharedPreferences("id_a", Context.MODE_PRIVATE);
        id_asignacion = sharedPreferences.getString("id_asignacion", "");
        System.out.println("id asignacion: "+id_asignacion);

        String fecha = obtenerFechaHoraActual();
        System.out.println("fecha"+ fecha);

        campo_buscar_placaH = findViewById(R.id.campo_buscar_placa);
        btnBuscar = findViewById(R.id.btnBuscarHistorial);

        textFecha = findViewById(R.id.textFechaH);
        textFecha.setText(fecha);



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placaBusqueda = campo_buscar_placaH.getText().toString();

                if (!placaBusqueda.equals("") ){
                    buscadorHistorial(placaBusqueda);
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa la placa", Toast.LENGTH_LONG).show();
                }
            }
        });

        dataConfig = new Config(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerDetalleHistorial);
        btnParqueaderoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(intencion);
            }
        });
        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), Entrada.class);
                startActivity(intencion);
            }
        });
        btnHistorial.setEnabled(false);

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
                finishAffinity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiObtenerHistorial();
    }

    private String calcularTiempo(String ingreso, String salida) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
        try {
            Date fechaIngreso = format.parse(ingreso);
            Date fechaSalida = format.parse(salida);
            long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
            long horas = diferencia / (60 * 60 * 1000);
            long minutos = (diferencia % (60 * 60 * 1000)) / (60 * 1000);
            return horas + "h " + minutos + "m";
        } catch (Exception e) {
            return "";
        }
    }


    public String obtenerFechaHoraActual() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        String fechaHoraActual = sdf.format(calendar.getTime());

        return fechaHoraActual;
    }

    public void apiObtenerHistorial(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-voce/obtenerHistorial.php");
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta api Historial: " + respuesta);
                    cargarListaHistorial(respuesta.getJSONArray("registros"));
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JOA Mani el servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_asignacion",id_asignacion);
                return params;
            }
        };
        queue.add(solicitud);
    }

    public void cargarListaHistorial(JSONArray datos) {
        listaDetalleHistorial = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            try {
                JSONObject historial = datos.getJSONObject(i);
                String entrada = historial.getString("create_entrada");
                String salida = historial.getString("salida");
                String tiempo = calcularTiempo(entrada,salida);
                listaDetalleHistorial.add(new DetalleHistorial(historial.getString("id"),historial.getString("placa"), entrada, salida, historial.getString("tipo_vehiculo"), historial.getString("Tarifa"), historial.getString("responsable"),tiempo ));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        adapter = new DetalleHistorialAdapter(listaDetalleHistorial);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void buscadorHistorial(String placa){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/buscarVehiculo.php?busqueda="+placa);
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject datos = new JSONObject(response);
                    JSONArray resultado = datos.getJSONArray("registros");
                    if (resultado.length() > 0){
                        cargarListaHistorial(resultado);
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