package com.example.parqueadero.vendedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.MainActivity;
import com.example.parqueadero.R;
import com.example.parqueadero.administrador.CrearParqueadero;
import com.example.parqueadero.utils.Config;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Entrada extends AppCompatActivity {
    Config dataConfig;
    Spinner spinnerTarifa;
    SharedPreferences sharedPreferences;
    String id_asignacion;
    EditText campo_placa;
    EditText campo_titular;
    String idTarifa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        ImageView btnParqueaderoV = findViewById(R.id.btnParqueaderoV);
        ImageView btnEntrada = findViewById(R.id.btnEntradaV);
        ImageView btnHistorial = findViewById(R.id.btnHistorialV);
        ImageView btnTarifa = findViewById(R.id.btnTarifasV);
        ImageView btnSalir = findViewById(R.id.btn_salirV);

        dataConfig = new Config(getApplicationContext());
        spinnerTarifa = findViewById(R.id.spinnerTarifaEntrada);
        sharedPreferences = getSharedPreferences("mis_datos", Context.MODE_PRIVATE);
        id_asignacion = sharedPreferences.getString("id_asignacion", "");
        System.out.println(id_asignacion);
        System.out.println(id_asignacion);
        System.out.println("si dio");

        String[] opciones = getResources().getStringArray(R.array.opciones_tarifa);

        // Crear un adaptador para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        // Especificar el diseño del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Configurar el adaptador en el spinner
        spinnerTarifa.setAdapter(adapter);
        btnParqueaderoV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(getApplicationContext(), MainActivityVendedor.class);
                startActivity(intencion);
            }
        });
        btnEntrada.setEnabled(false);
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
        apiObtenerTarifa();
        clickEnSpinner();
    }

    public void apiObtenerTarifa(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/Obtener.php");
        StringRequest solicitud = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    System.out.println("Respuesta api Tarifa " + respuesta);
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

    public void  clickEnSpinner(){
        spinnerTarifa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String opcionSeleccionada = (String) parentView.getItemAtPosition(position);
                System.out.println("opcion elejida: " + opcionSeleccionada);
                idTarifa(opcionSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getApplicationContext(), "Ninguna Opcion Seleccionada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void idTarifa(String opcionSeleccionada) {
        String opc = opcionSeleccionada;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = dataConfig.getEndPoint("/API-tarifas/ObtenerIdTarifa.php");

        // Crear una solicitud POST
        StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    String mensaje = respuesta.getString("message");
                    idTarifa = respuesta.getString("id_tarifa");
                    System.out.println(mensaje);
                    System.out.println(idTarifa);
                } catch (JSONException e) {
                    System.out.println("El servidor POST responde con error");
                    System.out.println(e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("El servidor POST responde con un error");
                System.out.println(error.getMessage());
            }
        }) {
            // Sobrescribir este método para pasar los parámetros en el cuerpo de la solicitud
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("tipo_vehiculo", opc);
                return params;
            }
        };

        // Agregar la solicitud a la cola de solicitudes
        queue.add(solicitud);
    }

    public void crearEntrada(View vista) {
        // Obtener los valores de los campos
        campo_placa = findViewById(R.id.campo_placa);
        campo_titular = findViewById(R.id.campo_titular);
        String placa = campo_placa.getText().toString();
        String titular = campo_titular.getText().toString();

        // Crear una solicitud POST para verificar la placa
        String urlVerificarPlaca = dataConfig.getEndPoint("/API-tarifas/VerificarPlaca.php");
        StringRequest solicitudVerificarPlaca = new StringRequest(Request.Method.POST, urlVerificarPlaca, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuesta = new JSONObject(response);
                    boolean status = respuesta.getBoolean("status");
                    if (status) {
                        // Si el status es verdadero, continuar con la creación del ticket
                        // Crear solicitud para insertar el ticket
                        String urlInsertarTicket = dataConfig.getEndPoint("/API-Ticket/insertTicket.php");
                        StringRequest solicitudInsertarTicket = new StringRequest(Request.Method.POST, urlInsertarTicket, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject resultado = new JSONObject(response);
                                    Toast.makeText(Entrada.this, resultado.getString("message"), Toast.LENGTH_SHORT).show();
                                    // Aquí puedes realizar cualquier otra acción después de crear el ticket
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("Error al conectar con la API para insertar el ticket");
                                Toast.makeText(Entrada.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("placa", placa);
                                params.put("id_asignacion", id_asignacion);
                                params.put("id_tarifa", idTarifa);
                                return params;
                            }
                        };
                        // Agregar la solicitud a la cola de solicitudes
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(solicitudInsertarTicket);
                        campo_placa.setText("");
                        campo_titular.setText("");
                    } else {

                        if(status){

                            // Crear una solicitud POST para verificar la placa
                            String urlVerificarPlaca = dataConfig.getEndPoint("/API-tarifas/VerificarEntrada.php");
                            StringRequest solicitudVerificarPlaca = new StringRequest(Request.Method.POST, urlVerificarPlaca, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject respuesta = new JSONObject(response);
                                        boolean status = respuesta.getBoolean("status");
                                        if (status) {

                                            Toast.makeText(Entrada.this, respuesta.getString("message"), Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (JSONException e) {
                                        System.out.println("Error al analizar la respuesta JSON de la API verificarplaca");
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    System.out.println("Error al conectar con la API verificarplaca");
                                    error.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Error en la conexión con el servidor", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("placa", placa);
                                    return params;
                                }
                            };

                            // Agregar la solicitud a la cola de solicitudes
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            queue.add(solicitudVerificarPlaca);

                        }else if(!status){

                            // Si el status es falso, crear el registro del vehículo
                            // Crear una solicitud POST para insertar el registro del vehículo
                            String urlInsertarRegistro = dataConfig.getEndPoint("/API-Ticket/insertRegistro.php");
                            StringRequest solicitudInsertarRegistro = new StringRequest(Request.Method.POST, urlInsertarRegistro, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject respuesta = new JSONObject(response);
                                        boolean status = respuesta.getBoolean("status");
                                        if (status) {
                                            // Si el status es verdadero, continuar con la creación del ticket
                                            // Crear solicitud para insertar el ticket
                                            String urlInsertarTicket = dataConfig.getEndPoint("/API-Ticket/insertTicket.php");
                                            StringRequest solicitudInsertarTicket = new StringRequest(Request.Method.POST, urlInsertarTicket, new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        JSONObject resultado = new JSONObject(response);
                                                        Toast.makeText(Entrada.this, resultado.getString("message"), Toast.LENGTH_SHORT).show();
                                                        // Aquí puedes realizar cualquier otra acción después de crear el ticket
                                                    } catch (JSONException e) {
                                                        throw new RuntimeException(e);
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    System.out.println("Error al conectar con la API para insertar el ticket");
                                                    Toast.makeText(Entrada.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                                                }
                                            }) {
                                                @Override
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> params = new HashMap<>();
                                                    params.put("placa", placa);
                                                    params.put("id_asignacion", id_asignacion);
                                                    params.put("id_tarifa", idTarifa);
                                                    return params;
                                                }
                                            };
                                            // Agregar la solicitud a la cola de solicitudes
                                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                            queue.add(solicitudInsertarTicket);
                                            campo_placa.setText("");
                                            campo_titular.setText("");
                                        } else {
                                            // Error al insertar el registro del vehículo
                                            // Aquí puedes manejar el error según sea necesario
                                            Toast.makeText(Entrada.this, respuesta.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        System.out.println("Error al analizar la respuesta JSON de la API insertarRegistro");
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    System.out.println("Error al conectar con la API insertarRegistro");
                                    error.printStackTrace();
                                    Toast.makeText(Entrada.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("placa", placa);
                                    params.put("responsable", titular);
                                    return params;
                                }
                            };

                            // Agregar la solicitud a la cola de solicitudes
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            queue.add(solicitudInsertarRegistro);

                        }
                    }
                } catch (JSONException e) {
                    System.out.println("Error al analizar la respuesta JSON de la API verificarplaca");
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error al conectar con la API verificarplaca");
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error en la conexión con el servidor", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("placa", placa);
                return params;
            }
        };

        // Agregar la solicitud a la cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(solicitudVerificarPlaca);
    }


}