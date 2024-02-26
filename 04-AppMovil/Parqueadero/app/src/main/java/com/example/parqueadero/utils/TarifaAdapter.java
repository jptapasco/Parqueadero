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

public class TarifaAdapter extends RecyclerView.Adapter<TarifaAdapter.ViewHolder> {
    private List<Tarifa> listaTarifa;

    public TarifaAdapter(List<Tarifa> listaTarifa) {
        this.listaTarifa = listaTarifa;
    }

    @NonNull
    @Override
    public TarifaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_tarifa, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull TarifaAdapter.ViewHolder holder, int position) {
        Tarifa tarifa = listaTarifa.get(position);
        holder.cargarTarifa(tarifa);
    }

    @Override
    public int getItemCount() {
        return listaTarifa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context contexto;
        TextView idTarifa;
        TextView vehiculo;
        TextView valorHora;

        public ViewHolder(View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            idTarifa = itemView.findViewById(R.id.etqIdTarifa);
            vehiculo = itemView.findViewById(R.id.etqVehiculoTarifa);
            valorHora = itemView.findViewById(R.id.etqValorTarifa);
        }

        public void cargarTarifa(Tarifa tarifa) {
            idTarifa.setText(tarifa.id);
            vehiculo.setText(tarifa.vehiculo);
            valorHora.setText(tarifa.valorTarifa);
        }
    }
}
