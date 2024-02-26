package com.example.parqueadero.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parqueadero.R;
import java.util.List;

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
                }
            });
        }
    }
}



