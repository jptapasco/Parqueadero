package com.example.parqueadero.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parqueadero.R;
import com.example.parqueadero.administrador.DeleteParqueadero;
import com.example.parqueadero.administrador.UpdateParqueadero;
import com.example.parqueadero.vendedor.Entrada;

import java.util.List;

public class ParqueaderoAdapter extends RecyclerView.Adapter<ParqueaderoAdapter.ViewHolder> {
    private List<Parqueadero> listaParqueadero;
    Context contexto;

    public ParqueaderoAdapter(List<Parqueadero> listaParqueadero,Context contexto) {
        this.listaParqueadero = listaParqueadero;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ParqueaderoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.formato_lista_parqueaderos, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ParqueaderoAdapter.ViewHolder holder, int position) {
        Parqueadero resumen = listaParqueadero.get(position);
        holder.cargarLista(resumen);
    }

    @Override
    public int getItemCount() {
        return listaParqueadero.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context contexto;
        TextView etqNit;
        TextView etqNombre;
        TextView etqDireccion;
        Button btnEditar;
        Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            contexto = itemView.getContext();
            etqNit = itemView.findViewById(R.id.etqNit);
            etqNombre = itemView.findViewById(R.id.etqNombre);
            etqDireccion = itemView.findViewById(R.id.etqDireccion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }

        public void cargarLista(Parqueadero resumen) {
            etqNit.setText(resumen.nit);
            etqNombre.setText(resumen.nombre);
            etqDireccion.setText(resumen.direccion);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Editar");
                    SharedPreferences sharedPreferences = contexto.getSharedPreferences("datos_edit",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nit", resumen.nit);
                    editor.putString("nombre", resumen.nombre);
                    editor.putString("direccion", resumen.direccion);
                    editor.putString("telefono", resumen.telefono);
                    editor.apply();
                    Intent intencion = new Intent(contexto, UpdateParqueadero.class);
                    contexto.startActivity(intencion);
                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Btn Eliminar");
                    SharedPreferences sharedPreferences = contexto.getSharedPreferences("datos_delete",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nit", resumen.nit);
                    editor.putString("nombre", resumen.nombre);
                    editor.putString("direccion", resumen.direccion);
                    editor.putString("telefono", resumen.telefono);
                    editor.apply();
                    Intent intencion = new Intent(contexto, DeleteParqueadero.class);
                    contexto.startActivity(intencion);
                }
            });
        }
    }
}
