package com.example.parqueadero.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parqueadero.R;
import com.example.parqueadero.administrador.InfoVendedores;
import com.example.parqueadero.administrador.MainActivityAdmin;
import com.example.parqueadero.administrador.UpdateParqueadero;
import com.example.parqueadero.administrador.UpdateVendedor;
import com.example.parqueadero.administrador.Vendedores;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> {
    private List<Persona> listaPersona;

    public PersonaAdapter(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    @NonNull
    @Override
    public PersonaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista_vendedores, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaAdapter.ViewHolder holder, int position) {
        Persona persona = listaPersona.get(position);
        holder.cargarPersona(persona);
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context contexto;
        TextView documento;
        TextView nombre;
        TextView apellido;
        Button btnInfo;
        Button btnEditarVendedor;
        Button btnDesligar;
        String cedula;
        Config dataConfig;

        public ViewHolder(View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            dataConfig = new Config(contexto);
            documento = itemView.findViewById(R.id.etqDocumento);
            nombre = itemView.findViewById(R.id.etqNombre);
            apellido = itemView.findViewById(R.id.etqApellido);
            btnInfo = itemView.findViewById(R.id.btnInfoVendedor);
            btnEditarVendedor = itemView.findViewById(R.id.btnEditarVendedor);
            btnDesligar = itemView.findViewById(R.id.btnDesligar);
        }

        public void cargarPersona(Persona persona) {
            documento.setText(persona.documento);
            nombre.setText(persona.nombre);
            apellido.setText(persona.apellido);

            cedula = persona.documento;
            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn info Vendedores");
                    SharedPreferences sharedPreferences = contexto.getSharedPreferences("info",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("cedula", persona.documento);
                    editor.putString("nombre", persona.nombre);
                    editor.putString("apellido", persona.apellido);
                    editor.putString("telefono", persona.telefono);
                    editor.apply();
                    Intent intencion = new Intent(contexto, InfoVendedores.class);
                    contexto.startActivity(intencion);
                }
            });

            btnEditarVendedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Editar Vendedores");
                    SharedPreferences sharedPreferences = contexto.getSharedPreferences("cc",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("cedula", persona.documento);
                    editor.apply();
                    Intent intencion = new Intent(contexto, UpdateVendedor.class);
                    contexto.startActivity(intencion);
                }
            });

            btnDesligar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Desligar Vendedores");
                    mostrarAlerta();
                }
            });
        }

        private void mostrarAlerta() {
            AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setMessage("¿Estás seguro?")
                    .setPositiveButton("Sí, estoy seguro", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            apiDesligar();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


        public void apiDesligar(){
            RequestQueue queue = Volley.newRequestQueue(contexto);
            String url = dataConfig.getEndPoint("/API-Personas/Desligar.php");
            System.out.println("cedula: "+cedula);
            StringRequest consulta = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject resultado = new JSONObject(response);
                        System.out.println("respuesta: "+resultado);
                        boolean status = resultado.getBoolean("status");
                        if (status){
                            mostrarAlertaOk();
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
                    params.put("cedula",cedula);
                    return params;
                }
            };
            queue.add(consulta);
        }

        private void mostrarAlertaOk() {
            AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setTitle("EXITO");
            builder.setMessage("Vendedor desligado con exito");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intencion = new Intent(contexto, Vendedores.class);
                    contexto.startActivity(intencion);
                }
            });

            AlertDialog alerta = builder.create();
            alerta.show();
        }

        private void mostrarAlertaError() {
            AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setTitle("ERROR");
            builder.setMessage("No se pudo completar esta accion");

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
}
