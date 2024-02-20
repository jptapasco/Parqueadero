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

public class ParqueaderoAdapter extends RecyclerView.Adapter<ParqueaderoAdapter.ViewHolder> {
    private List<Parqueadero> listaParqueadero;
    public ParqueaderoAdapter(List<Parqueadero> listaParqueadero){this.listaParqueadero = listaParqueadero;}

    @NonNull
    @Override
    public ParqueaderoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista_parqueaderos,parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ParqueaderoAdapter.ViewHolder holder, int position){
        Parqueadero resumen = listaParqueadero.get(position);
        holder.cargarLista(resumen);
    }

    @Override
    public  int getItemCount(){return listaParqueadero.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{
        Context contexto;
        TextView etqNit;
        TextView etqNombre;
        TextView etqDireccion;
        Button btnEditar;
        Button btnEliminar;
        public ViewHolder(View itemView){
            super(itemView);

            contexto = itemView.getContext();
            etqNit = itemView.findViewById(R.id.etqNit);
            etqNombre = itemView.findViewById(R.id.etqNombre);
            etqDireccion = itemView.findViewById(R.id.etqDireccion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }

        public void cargarLista(Parqueadero resumen){
            etqNit.setText(resumen.nit);
            etqNombre.setText(resumen.nombre);
            etqDireccion.setText(resumen.direccion);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Editar");
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Eliminar");
                }
            });
        }
    }
}
