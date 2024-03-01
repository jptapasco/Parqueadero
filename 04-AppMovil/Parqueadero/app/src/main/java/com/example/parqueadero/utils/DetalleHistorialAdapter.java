package com.example.parqueadero.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parqueadero.R;
import com.example.parqueadero.administrador.InfoVendedores;
import com.example.parqueadero.vendedor.Imprimir;

import java.util.List;

public class DetalleHistorialAdapter extends RecyclerView.Adapter<DetalleHistorialAdapter.ViewHolder> {
    private List<DetalleHistorial> listaDetalleHistorial;

    public DetalleHistorialAdapter(List<DetalleHistorial> listaDetalleHistorial) {
        this.listaDetalleHistorial = listaDetalleHistorial;
    }

    @NonNull
    @Override
    public DetalleHistorialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_detalle_historial, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleHistorialAdapter.ViewHolder holder, int position) {
        DetalleHistorial detalleHistorial = listaDetalleHistorial.get(position);
        holder.cargarHistorial(detalleHistorial);
    }

    @Override
    public int getItemCount() {
        return listaDetalleHistorial.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context contexto;
        TextView etqTicket;
        TextView etqTipoVehiculo;
        TextView etqTarifa;
        TextView etqPlaca;
        TextView etqTitular;
        TextView etqIngreso;
        TextView etqSalida;
        TextView etqEstancia;
        Button btnImprimir;
        public ViewHolder(View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            etqTicket = itemView.findViewById(R.id.etqTicket);
            etqTipoVehiculo = itemView.findViewById(R.id.etqTipoVehiculo);
            etqTarifa = itemView.findViewById(R.id.etqTarifa);
            etqPlaca = itemView.findViewById(R.id.etqPlaca);
            etqTitular = itemView.findViewById(R.id.etqTitular);
            etqIngreso = itemView.findViewById(R.id.etqIngreso);
            etqSalida = itemView.findViewById(R.id.etqSalida);
            etqEstancia = itemView.findViewById(R.id.etqEstancia);
            btnImprimir = itemView.findViewById(R.id.btnImprimir);
        }

        public void cargarHistorial(DetalleHistorial detalleHistorial) {
            etqTicket.setText(detalleHistorial.id);
            etqTipoVehiculo.setText(detalleHistorial.tipoVehiculo);
            etqTarifa.setText(detalleHistorial.tarifa);
            etqPlaca.setText(detalleHistorial.placa);
            etqTitular.setText(detalleHistorial.responsable);
            etqIngreso.setText(detalleHistorial.entrada);
            etqSalida.setText(detalleHistorial.salida);
            etqEstancia.setText(detalleHistorial.tiempo);

            btnImprimir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    System.out.println("Btn imprimir");
                    SharedPreferences sharedPreferences = contexto.getSharedPreferences("infoHistorial",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ticket", detalleHistorial.id);
                    editor.putString("placa", detalleHistorial.placa);
                    editor.putString("vehiculo", detalleHistorial.tipoVehiculo);
                    editor.putString("tarifa", detalleHistorial.tarifa);
                    editor.putString("tiempo", detalleHistorial.tiempo);
                    editor.putString("entrada", detalleHistorial.entrada);
                    editor.putString("salida", detalleHistorial.salida);
                    editor.putString("titular", detalleHistorial.responsable);

                    editor.apply();
                    Intent intencion = new Intent(contexto, Imprimir.class);
                    contexto.startActivity(intencion);

                }
            });
        }
    }
}
