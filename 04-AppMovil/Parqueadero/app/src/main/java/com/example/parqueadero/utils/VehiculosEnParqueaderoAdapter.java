package com.example.parqueadero.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehiculosEnParqueaderoAdapter extends RecyclerView.Adapter<VehiculosEnParqueaderoAdapter.ViewHolder> {
    private List<DetalleHistorial> listaVehiculosEn;

    public VehiculosEnParqueaderoAdapter(List<DetalleHistorial> listaVehiculosEn) {
        this.listaVehiculosEn = listaVehiculosEn;
    }

    @NonNull
    @Override
    public VehiculosEnParqueaderoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_autos_en_pk, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculosEnParqueaderoAdapter.ViewHolder holder, int position) {
        DetalleHistorial detalleHistorial = listaVehiculosEn.get(position);
        holder.cargarHistorial(detalleHistorial);
    }

    @Override
    public int getItemCount() {
        return listaVehiculosEn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context contexto;
        TextView etqTicketEn;
        Config dataConfig;
        TextView etqTipoVehiculoEn;
        TextView etqTarifaEn;
        TextView etqPlacaEn;
        TextView etqTitularEn;
        TextView etqIngresoEn;
        TextView etqEstanciaEn;
        Button btnSalidaPk;

        public ViewHolder(View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            etqTicketEn = itemView.findViewById(R.id.etqTicketEn);
            etqTipoVehiculoEn = itemView.findViewById(R.id.etqTipoVehiculoEn);
            etqTarifaEn = itemView.findViewById(R.id.etqTarifaEn);
            etqPlacaEn = itemView.findViewById(R.id.etqPlacaEn);
            etqTitularEn = itemView.findViewById(R.id.etqTitularEn);
            etqIngresoEn = itemView.findViewById(R.id.etqIngresoEn);
            etqEstanciaEn = itemView.findViewById(R.id.etqEstanciaEn);
            btnSalidaPk = itemView.findViewById(R.id.btnSalidaPk);
        }

        public void cargarHistorial(DetalleHistorial detalleHistorial) {
            etqTicketEn.setText(detalleHistorial.id);
            etqTipoVehiculoEn.setText(detalleHistorial.tipoVehiculo);
            etqTarifaEn.setText(detalleHistorial.tarifa);
            etqPlacaEn.setText(detalleHistorial.placa);
            etqTitularEn.setText(detalleHistorial.responsable);
            etqEstanciaEn.setText(detalleHistorial.tiempo);
            etqIngresoEn.setText(detalleHistorial.entrada);

            btnSalidaPk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("BTN Salida");
                    mostrarAlerta(detalleHistorial);
                }
            });
        }

        private void mostrarAlerta(DetalleHistorial detalleHistorial) {
            String tarifa = detalleHistorial.tarifa;
            String tiempo = detalleHistorial.tiempo;
            double tarifaDouble = Double.parseDouble(tarifa);

            // Datos de entrada
            double tarifaPorHora = tarifaDouble; // Tarifa
            String tiempoT = tiempo;

            // Extraer horas y minutos del tiempo
            int horas = 0;
            int minutos = 0;

            if (tiempoT.contains("h")) {
                String[] partesTiempo = tiempoT.split("h");
                horas = Integer.parseInt(partesTiempo[0].trim());

                if (partesTiempo[1].contains("m")) {
                    minutos = Integer.parseInt(partesTiempo[1].replace("m", "").trim());
                }
            } else if (tiempoT.contains("m")) {
                minutos = Integer.parseInt(tiempoT.replace("m", "").trim());
            }

            // Calcular la tarifa
            double tarifaTotal = (horas * tarifaPorHora) + ((minutos > 0) ? tarifaPorHora : 0);

            AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setMessage("¿Deseas Cobrar?"+"\nTotal de horas:  "+ detalleHistorial.tiempo+"\nPrecio a pagar: "+tarifaTotal)
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
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

        public void apiDarSalida(){
            dataConfig = new Config(contexto);
            RequestQueue queue = Volley.newRequestQueue(contexto);
            String url = dataConfig.getEndPoint("/API-voce/obtenerParqueadero.php");
            StringRequest solicitud = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject respuesta = new JSONObject(response);
                        System.out.println("Respuesta api Vehiculos EN: " + respuesta);
                    } catch (JSONException e) {
                        System.out.println("El servidor POST responde con error");
                        System.out.println(e.getMessage());
                        Toast.makeText(contexto, "Error en datos del servidor: " + e.getMessage(), Toast.LENGTH_LONG).show();
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
                    params.put("id_asignacion",);
                    return params;
                }
            };
            queue.add(solicitud);
        }

    }
}



